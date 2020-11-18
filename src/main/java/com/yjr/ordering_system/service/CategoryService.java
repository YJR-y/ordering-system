package com.yjr.ordering_system.service;

import com.yjr.ordering_system.entity.ProductCategory;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * 类目表
 * @author yjr
 * @since 2020/10/28 16:13
 */
public interface CategoryService {

    /**
     * 根据id查找类目表中的一个数据
     * @param id
     * @return
     */
    ProductCategory findOne(Integer id);

    /**
     * 查找类目表中全部数据
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 通过单个或者多个类目编号进行查询(类目编号是唯一的)
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 添加或者修改类目表中的数据
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);

}
