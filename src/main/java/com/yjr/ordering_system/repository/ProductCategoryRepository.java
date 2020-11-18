package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 连接数据库商品类目表格 product_category
 * @author yjr
 * @since 2020/10/27 17:21
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    /**
     * 传入一个或者多个类目编号然后进行查找
     * select * from product_category where category_type in (1, 4)
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
