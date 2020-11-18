package com.yjr.ordering_system.service;

import com.yjr.ordering_system.dto.CartDTO;
import com.yjr.ordering_system.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 *
 * @author yjr
 * @since 2020/10/29 16:19
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有已上架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品(不管是否上架 全部商品进行查询)
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 添加或者修改商品
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 添加商品库存
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减少商品库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
