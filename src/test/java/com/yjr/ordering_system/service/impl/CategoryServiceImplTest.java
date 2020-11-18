package com.yjr.ordering_system.service.impl;

import com.yjr.ordering_system.entity.ProductCategory;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yjr
 * @since 2020/10/28 16:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest extends TestCase {

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void testFindOne() {
        ProductCategory one = categoryService.findOne(1);
        assert one.getCategoryId() == 1;
    }

    @Test
    public void testFindAll() {
        List<ProductCategory> list = categoryService.findAll();
        assert list.size() != 0;
    }

    @Test
    public void testFindByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2));
        assert list.size() != 0;
    }

    @Test
    public void testSave() {
        ProductCategory productCategory = new ProductCategory("男生专享", 20);
        ProductCategory save = categoryService.save(productCategory);
        assert save !=null;
    }

}