package com.yjr.ordering_system.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品在用户使用的页面显示的属性
 * 商品详情
 * @author yjr
 * @since 2020/11/03 15:29
 */
@Data
public class ProductInfoVO {

    /**
     * 商品Id
     */
    @JsonProperty("id")
    private String productId;

    /**
     * 商品名字
     */
    @JsonProperty("name")
    private String productName;

    /**
     * 商品价格
     */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**
     * 商品描述
     */
    @JsonProperty("description")
    private  String productDescription;

    /**
     * 商品图片链接
     */
    @JsonProperty("icon")
    private String productIcon;

    @Override
    public String toString() {
        return "ProductInfoVO{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productIcon + '\'' +
                '}';
    }
}
