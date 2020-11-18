package com.yjr.ordering_system.exception;

import com.yjr.ordering_system.enums.ResultEnums;

/**
 * @author yjr
 * @since 2020/11/09 16:39
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnums resultEnums) {
        super(resultEnums.getMessage());
        this.code = resultEnums.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
    }

}
