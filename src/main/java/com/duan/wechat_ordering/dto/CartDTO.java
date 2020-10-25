package com.duan.wechat_ordering.dto;


import lombok.Data;

@Data
public class CartDTO {
    //购物车

    /** 商品ID*/
    private String ProductId;

    /** 商品数量*/
    private Integer ProductQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        ProductId = productId;
        ProductQuantity = productQuantity;
    }
    public CartDTO(){

    }
}
