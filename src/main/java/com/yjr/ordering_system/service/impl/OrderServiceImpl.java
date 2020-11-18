package com.yjr.ordering_system.service.impl;

import com.yjr.ordering_system.converter.OrderMaster2OrderDTOConverter;
import com.yjr.ordering_system.dto.CartDTO;
import com.yjr.ordering_system.dto.OrderDTO;
import com.yjr.ordering_system.entity.OrderDetail;
import com.yjr.ordering_system.entity.OrderMaster;
import com.yjr.ordering_system.entity.ProductInfo;
import com.yjr.ordering_system.enums.OrderStatusEnum;
import com.yjr.ordering_system.enums.PayStatusEnum;
import com.yjr.ordering_system.enums.ProductStatusEnum;
import com.yjr.ordering_system.enums.ResultEnums;
import com.yjr.ordering_system.exception.SellException;
import com.yjr.ordering_system.repository.OrderDetailRepository;
import com.yjr.ordering_system.repository.OrderMasterRepository;
import com.yjr.ordering_system.service.OrderService;
import com.yjr.ordering_system.service.ProductService;
import com.yjr.ordering_system.utils.KeyUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.text.CollatorUtilities;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yjr
 * @since 2020/11/09 15:28
 */

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum = sum + ints[i];
        }
        System.out.println(sum);
    }

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return orderDTO
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        // 商品总价变量 默认值为0
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //生成整个订单的订单编号
        String orderId = KeyUtil.genUniqueKey();

        List<CartDTO> cartDTOList = new ArrayList<>();
        //1,需要查询商品(数量，价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            //判断商品是否存在
            if (productInfo == null) {
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            // 单价
            BigDecimal productPrice = productInfo.getProductPrice();
            // 数量
            BigDecimal productQuantity = new BigDecimal(orderDetail.getProductQuantity());
            // 单项总价
            BigDecimal itemTotalAmount = productPrice.multiply(productQuantity);
            // orderAmount = orderAmount + itemTotalAmount;
            orderAmount = orderAmount.add(itemTotalAmount);

            //订单存入买家订单数据库中
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);

            //获取到商品id和购买的商品数量
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        //3.写入订单数据库 (OrderMaster和 OrderDetail两个表格中)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        // orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMasterRepository.save(orderMaster);
        //4.下单成功后需要扣除库存

        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    /**
     * 查询单个订单
     *
     * @param orderId
     * @return orderDTO
     */
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnums.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnums.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**
     * 查询订单列表
     */
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter
                .convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    /**
     * 用户取消订单
     *
     * @param orderDTO
     * @return orderDTO
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确 orderId{}, orderStatus{}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        //如果更新失败
        if (updateResult == null) {
            log.error("【取消订单】修改订单失败 orderMaster{}", orderMaster);
            throw new SellException(ResultEnums.ORDER_UPDATE_ERROR);
        }
        //3.取消的订单添加回库存中
        //先判断订单中是否有商品
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中没有商品详情 orderDTO{}", orderDTO);
            throw new SellException(ResultEnums.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map((OrderDetail orderDetail) ->
                        new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //4.如果已经支付需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;
    }

    /**
     * 完结订单
     * @param orderDTO
     * @return orderDTO
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getPayStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确 orderId{},orderStatus{}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        //如果更新失败
        if (updateResult == null) {
            log.error("【完结订单】更新失败 orderMaster{}",orderMaster);
            throw new SellException(ResultEnums.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    /**
     * 订单支付
     * @param orderDTO
     * @return orderDTO
     */
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确 orderId{},orderStatus{}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnums.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        //判断是不是待支付订单
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】订单不为待支付订单 orderDTO{}",orderDTO);
            throw new SellException(ResultEnums.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        //如果更新失败
        if (updateResult == null) {
            log.error("【支付订单】更新失败 orderMaster{}",orderMaster);
            throw new SellException(ResultEnums.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }
}
