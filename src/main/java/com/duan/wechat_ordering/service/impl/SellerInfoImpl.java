package com.duan.wechat_ordering.service.impl;

import com.duan.wechat_ordering.entity.SellerInfo;
import com.duan.wechat_ordering.repository.SellerInfoRepository;
import com.duan.wechat_ordering.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findSellerInfoByOpenId(String openId) {
        return sellerInfoRepository.findByOpenId(openId);
    }
}
