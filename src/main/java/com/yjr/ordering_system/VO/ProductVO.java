package com.yjr.ordering_system.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品(包含类目)
 * 在用户端显示的类目
 * @author yjr
 * @since 2020/11/03 15:22
 */
@Data
public class ProductVO {

    /**
     * 类目名字
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 类目编号
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * 产品信息列表
     */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
