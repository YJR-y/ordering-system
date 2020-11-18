package com.yjr.ordering_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 购物车
 * @author yjr
 * @since 2020/11/10 16:13
 */
@Data
@AllArgsConstructor
@ToString
public class CartDTO {

    /**
     * 商品Id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

}
