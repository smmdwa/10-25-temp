package com.duan.wechat_ordering.enums;


import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(1,"上架"),
    DOWN(0,"下架");

    private Integer code;

    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
