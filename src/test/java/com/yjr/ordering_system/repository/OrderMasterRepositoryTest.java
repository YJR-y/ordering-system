package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.OrderMaster;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author yjr
 * @since 2020/11/06 16:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest extends TestCase {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String OPENID = "123";

    @Test
    public void testFindByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(1, 5);

        Page<OrderMaster> byBuyerOpenid = orderMasterRepository.findByBuyerOpenid(OPENID, pageRequest);
        System.out.println(byBuyerOpenid.getTotalElements());
        assert byBuyerOpenid.getTotalElements() != 0;
    }

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerPhone("11111111111");
        orderMaster.setBuyerAddress("上海松江");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(18.2));
        orderMasterRepository.save(orderMaster);
    }
}