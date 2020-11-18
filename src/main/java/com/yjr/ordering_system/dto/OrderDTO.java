package com.yjr.ordering_system.dto;

import com.yjr.ordering_system.entity.OrderDetail;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yjr
 * @since 2020/11/09 14:45
 */
@Data
@ToString
public class OrderDTO {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 买家名称
     */
    private String buyerName;

    /**
     *买家手机号
     */
    private String buyerPhone;

    /**
     *买家家庭地址
     */
    private String buyerAddress;

    /**
     *买家微信openID
     */
    private String buyerOpenid;

    /**
     *订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态默认0新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态，默认0为未付款
     */
    private Integer payStatus;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    /**
     * 订单项中的数据
     */
    @Transient
    private List<OrderDetail> orderDetailList;

}
