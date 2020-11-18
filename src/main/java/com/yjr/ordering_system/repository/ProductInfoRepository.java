package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 连接数据库商品属性表格 product_info
 * @author yjr
 * @since 2020/10/29 15:36
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
