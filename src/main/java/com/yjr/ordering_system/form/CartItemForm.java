package com.yjr.ordering_system.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * @author yjr
 * @since 2020/11/17 下午8:26
 */
@Data
public class CartItemForm {

    @NotBlank
    private String productId;

    @Min(1)
    private Integer productQuantity;

}
