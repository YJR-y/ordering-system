package com.yjr.ordering_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 商品状态枚举
 * @author yjr
 * @since 2020/11/02 14:11
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum {
    UP(0 , "在架"),
    DOWN(1,"下架"),
    ;

    private final Integer code;

    private final String message;

}
