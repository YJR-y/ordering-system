package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yjr
 * @since 2020/10/27 17:21
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

}
