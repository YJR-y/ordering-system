package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.OrderDetail;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yjr
 * @since 2020/11/09 10:41
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest extends TestCase {

    @Autowired
   private OrderDetailRepository orderDetailRepository;

    @Test
    public void testFindByOrderId() {
        List<OrderDetail> byOrderId = orderDetailRepository.findByOrderId("11111");
        assert byOrderId.size() != 0;
    }

    @Test
    public void saveTest() {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234545");
        orderDetail.setOrderId("111591");
        orderDetail.setProductIcon("http://xxx.jpj");
        orderDetail.setProductName("皮蛋");
        orderDetail.setProductQuantity(40);
        orderDetail.setProductPrice(new BigDecimal(4.2));
        orderDetail.setProductId("1115612");
        orderDetailRepository.save(orderDetail);
    }
}