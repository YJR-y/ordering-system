package com.yjr.ordering_system.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 * @author yjr
 * @since 2020/11/05 17:00
 */
@Entity
@Data
@ToString
public class OrderDetail {

    /**
     * 单个商品订单的ID
     */
    @Id
    private String detailId;

    /**
     *整个订单ID
     */
    private String orderId;

    /**
     *商品ID
     */
    private String productId;

    /**
     *商品名称
     */
    private String productName;

    /**
     *商品价格
     */
    private BigDecimal productPrice;

    /**
     *商品数量
     */
    private Integer productQuantity;

    /**
     *商品图片链接
     */
    private String productIcon;

}
