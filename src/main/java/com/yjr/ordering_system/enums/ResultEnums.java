package com.yjr.ordering_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yjr
 * @since 2020/11/09 16:42
 */
@Getter
@AllArgsConstructor
public enum ResultEnums {

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "商品不足"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    ORDER_UPDATE_ERROR(15, "订单更新失败"),

    ORDER_DETAIL_EMPTY(16, "订单中没有商品详情"),

    ORDER_PAY_ERROR(17,"订单支付失败"),

    ORDER_PAY_STATUS_ERROR(18, "订单支付状态不正确"),

    CART_EMPTY(19,"购物车为空"),

    OPENID_EMPTY(20, "openId为空"),
    ;

    private final Integer code;

    private final String message;
}
