package com.duan.wechat_ordering.service;

import com.duan.wechat_ordering.dto.OrderDTO;

public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid,String orderId);

}
