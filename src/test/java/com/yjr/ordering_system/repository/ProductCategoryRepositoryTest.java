package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yjr
 * @since 2020/10/27 17:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        repository.findById(1).ifPresent(System.out::println);
    }
}