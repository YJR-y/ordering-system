package com.yjr.ordering_system.VO;

import lombok.Data;

/**
 * view object
 * http请求返回最外层的对象
 *
 * mvc: model view controller
 *
 * @author yjr
 * @since 2020/11/03 14:25
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

    public static <T> ResultVO<T> ok(T data) {
        ResultVO<T> vo = new ResultVO<>();
        vo.setCode(200);
        vo.setMsg("SUCCESS");
        vo.setData(data);
        return vo;
    }

}
