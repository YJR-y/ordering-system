package com.yjr.ordering_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yjr
 * @since 2020/11/05 16:30
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;
    private final Integer code;

    private final String message;

}
