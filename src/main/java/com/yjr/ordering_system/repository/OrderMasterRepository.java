package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 卖家端口连接的数据库订单表 order_master
 * @author yjr
 * @since 2020/11/06 14:42
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{

    /**
     * 通过买家的openID来进行查询订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid , Pageable pageable);
}
