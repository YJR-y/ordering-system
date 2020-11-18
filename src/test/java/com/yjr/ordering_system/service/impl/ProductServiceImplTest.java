package com.yjr.ordering_system.service.impl;

import com.yjr.ordering_system.entity.ProductInfo;
import com.yjr.ordering_system.enums.ProductStatusEnum;
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
 * @since 2020/11/02 14:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest extends TestCase {

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void testFindOne() {
        productService.findOne("123");
    }

    @Test
    public void testFindPuAll() {
        productService.findUpAll();
    }

    @Test
    public void testFindAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> all = productService.findAll(pageRequest);
        System.out.println(all.getTotalElements());
    }

    @Test
    public void testSave() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("黄焖鸡米饭");
        productInfo.setProductPrice(new BigDecimal(20.2));
        productInfo.setProductStock(99);
        productInfo.setProductDescription("超级好吃的黄焖鸡米饭");
        productInfo.setProductIcon("http://xx.jpj");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(111);
        productInfo.setProductId("1234");
        productService.save(productInfo);
    }
}