package com.yjr.ordering_system.repository;

import com.yjr.ordering_system.entity.ProductCategory;
import com.yjr.ordering_system.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yjr
 * @since 2020/10/27 17:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository repository;

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    public void findOneTest() {
        ProductCategory one = repository.findOne(1);
        System.out.println(one.toString());
    }

    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("男生最爱", 4);
        ProductCategory save = repository.save(productCategory);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        List<ProductCategory> byCategoryTypeIn = repository.findByCategoryTypeIn(list);

        assert byCategoryTypeIn.size() != 0;
    }

    @Test
    public void testFindByCategoryTypeIn() {
    }

    @Test
    public void saveTest2() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝 非常好吃的粥");
        productInfo.setProductIcon("http://xxx.jpj");
        productInfo.setProductStock(0);
        productInfo.setCategoryType(1);

        ProductInfo save = productInfoRepository.save(productInfo);
        assert save != null;
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
        assert list.size() != 0;
    }
}