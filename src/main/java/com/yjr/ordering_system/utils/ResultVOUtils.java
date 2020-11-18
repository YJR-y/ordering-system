package com.yjr.ordering_system.utils;

import com.yjr.ordering_system.VO.ProductVO;
import com.yjr.ordering_system.VO.ResultVO;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yjr
 * @since 2020/11/04 15:00
 */
public class ResultVOUtils {

    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO<Object> success() {
        return success(null);
    }

    public static ResultVO<Object> error(Integer code, String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
