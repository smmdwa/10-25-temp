package com.duan.wechat_ordering.service;

import com.duan.wechat_ordering.entity.SellerInfo;

public interface SellerInfoService {

    SellerInfo findSellerInfoByOpenId (String openId);
}
