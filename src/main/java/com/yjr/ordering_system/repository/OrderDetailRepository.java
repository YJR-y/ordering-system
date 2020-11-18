package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.OrderDetail;
import com.yjr.ordering_system.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 买家端订单数据链接数据库 order_detail
 * @author yjr
 * @since 2020/11/06 15:31
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 先通过master表找到orderID 然后通过orderID在Detail表中具体信息
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
