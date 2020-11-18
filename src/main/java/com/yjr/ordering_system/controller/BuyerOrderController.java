package com.yjr.ordering_system.controller;

import com.yjr.ordering_system.VO.OrderCreateVO;
import com.yjr.ordering_system.VO.ResultVO;
import com.yjr.ordering_system.converter.OrderForm2OrderDTOConverter;
import com.yjr.ordering_system.dto.OrderDTO;
import com.yjr.ordering_system.enums.ResultEnums;
import com.yjr.ordering_system.exception.SellException;
import com.yjr.ordering_system.form.OrderForm;
import com.yjr.ordering_system.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yjr
 * @since 2020/11/13 15:39
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResultVO<OrderCreateVO> create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) {
        //判断表单校验是否错误
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确 orderForm{}",orderForm);
            throw new SellException(ResultEnums.PARAM_ERROR.getCode()
                    ,bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.orderFormToOrderDTO(orderForm);
        //判断购物车是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnums.CART_EMPTY);
        }
        OrderDTO creatResult = orderService.create(orderDTO);
        OrderCreateVO orderCreateVO = new OrderCreateVO();
        orderCreateVO.setOrderId(creatResult.getOrderId());
        return ResultVO.ok(orderCreateVO);
    }

    //订单列表

    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam(value = "openId") String openId,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("size") Integer size) {
        //校验openId是否为空
        if (StringUtils.isEmpty(openId)) {
            log.error("【查询订单列表】openId为空");
            throw new SellException(ResultEnums.OPENID_EMPTY);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> list = orderService.findList(openId, pageRequest);
        return null;

    }
    //订单详情

    //取消订单

}
