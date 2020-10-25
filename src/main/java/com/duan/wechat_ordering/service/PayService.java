package com.duan.wechat_ordering.service;

import com.duan.wechat_ordering.dto.OrderDTO;

public interface PayService {

    void create(OrderDTO orderDTO);

}
