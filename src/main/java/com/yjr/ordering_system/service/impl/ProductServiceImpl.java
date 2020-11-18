package com.yjr.ordering_system.service.impl;

import com.yjr.ordering_system.dto.CartDTO;
import com.yjr.ordering_system.entity.ProductInfo;
import com.yjr.ordering_system.enums.ProductStatusEnum;
import com.yjr.ordering_system.enums.ResultEnums;
import com.yjr.ordering_system.exception.SellException;
import com.yjr.ordering_system.repository.ProductInfoRepository;
import com.yjr.ordering_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 商品
 *
 * @author yjr
 * @since 2020/10/30 15:44
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository repository;

    /**
     * 通过商品ID查询单个商品
     *
     * @param productId
     * @return
     */
    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    /**
     * 查询所有在上架的商品信息
     *
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     * 查询所有商品信息
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * 添加或者修改商品信息
     *
     * @param productInfo1
     * @return
     */
    @Override
    public ProductInfo save(ProductInfo productInfo1) {
        return repository.save(productInfo1);
    }

    /**
     * 添加库存
     *
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    /**
     * 减少库存
     *
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            //判断商品是否为空
            if (productInfo == null) {
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }
            //商品库存 - 购物车中的商品数量
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnums.PRODUCT_STOCK_ERROR);
            }
            //减掉的库存再设置进库存中
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }
}
