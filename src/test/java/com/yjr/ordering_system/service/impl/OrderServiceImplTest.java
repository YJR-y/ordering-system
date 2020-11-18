package com.yjr.ordering_system.service.impl;

import com.yjr.ordering_system.dto.OrderDTO;
import com.yjr.ordering_system.entity.OrderDetail;
import com.yjr.ordering_system.repository.OrderMasterRepository;
import com.yjr.ordering_system.utils.KeyUtil;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjr
 * @since 2020/11/11 14:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest extends TestCase {

    @Autowired
    private OrderServiceImpl orderService;

    private final String buyerOpenId = "666666";

    private final String ORDER_ID = "1605081227083806979";

    @Test
    public void testCreate() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("北京");
        orderDTO.setBuyerName("杨");
        orderDTO.setBuyerOpenid(buyerOpenId);
        orderDTO.setBuyerPhone("12345678901");

        //购物车
        List<OrderDetail> orderList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("111");
        orderDetail.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1234");
        o2.setProductQuantity(5);

        orderList.add(orderDetail);
        orderList.add(o2);

        orderService.create(orderDTO);
        log.info("【创建订单】result={}", createResult());
    }

    @Test
    public void testFindOne() {
        OrderDTO one = orderService.findOne(ORDER_ID);
        System.out.println(one);
    }

    @Test
    public void testFindList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> list = orderService.findList(buyerOpenId, pageRequest);
        assert list.getTotalElements() != 0;
    }

    @Test
    public void testCancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        orderService.cancel(orderDTO);
    }

    @Test
    public void testFinish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        orderService.finish(orderDTO);

    }

    @Test
    public void testPaid() {
    }
}