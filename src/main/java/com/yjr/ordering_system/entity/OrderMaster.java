package com.yjr.ordering_system.entity;

import com.yjr.ordering_system.enums.OrderStatusEnum;
import com.yjr.ordering_system.enums.PayStatusEnum;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * 订单项
 * @author yjr
 * @since 2020/11/05 15:53
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@ToString
public class OrderMaster {
    /**
     * 订单id
     */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态，默认0为未付款
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

}
