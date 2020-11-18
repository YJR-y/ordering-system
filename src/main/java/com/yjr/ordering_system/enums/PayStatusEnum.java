package com.yjr.ordering_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yjr
 * @since 2020/11/05 16:37
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private final Integer code;

    private final String message;

}
