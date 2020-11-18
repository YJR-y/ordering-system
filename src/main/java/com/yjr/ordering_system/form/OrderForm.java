package com.yjr.ordering_system.form;

import com.yjr.ordering_system.dto.CartDTO;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * 表单验证
 * @author yjr
 * @since 2020/11/17 下午3:49
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String  phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家openID
     */
    @NotEmpty(message = "openId必填")
    private String openId;

    /**
     * 购物车
     */
    @Valid
    @NotEmpty(message = "购物车不能为空")
    private List<CartItemForm> items;
}
