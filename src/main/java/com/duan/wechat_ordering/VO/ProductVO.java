package com.duan.wechat_ordering.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(包含类目)
 * Created by 廖师兄
 * 2017-05-12 14:20
 */
@Data
public class ProductVO implements Serializable {


    private static final long serialVersionUID = 6793660807572554543L;
    /** JsonProperty的作用就是使返回给前端的标签为name 而不是categoryName  **/
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
