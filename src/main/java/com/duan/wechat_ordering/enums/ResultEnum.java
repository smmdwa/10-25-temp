package com.duan.wechat_ordering.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_SOTCK_ERROR(11,"库存不正确"),
    ORDERDETAIL_NOT_EXIST(12,"订单详情不存在"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单无商品详情"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态错误"),
    CART_EMPTY_ERROR(18,"购物车不能为空"),
    ORDER_OWNER_ERROR(19,"订单OPENID不符"),
    CANCEL_ORDER_ERROR(20,"取消的订单为空订单，无法取消"),
    WECHAT_MP_ERROR(21,"微信MP错误"),
    PRODUCT_STATUS_ERROR(22,"商品上/下架状态错误"),
    LOGIN_SELLER_NOT_EXIT(23,"登陆失败！卖家不存在"),
    LOGOUT_SUCCESS(24,"登出成功！"),
    LOGIN_NEEDED(25,"还没有登录！"),
    LOGIN_ERROR(26,"登陆失败"),
    ;


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
