package com.duan.wechat_ordering.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {

    private String ProductId;

    /** 商品名字 */
    private String ProductName;

    /** 商品价格*/
    private BigDecimal ProductPrice;

    /** 商品库存*/
    private Integer ProductStock;

    /** 商品描述*/
    private String ProductDescription;

    /** 商品小图 图标*/
    private String ProductIcon;

    /** 类目编号  */
    private Integer CategoryType;
}
