package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.SellerInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    void findByOpenId() {
        String openId="abc";
        SellerInfo sellerInfo=sellerInfoRepository.findByOpenId(openId);
        System.out.println(sellerInfo);
    }

    @Test
    void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setOpenId("123");
        sellerInfo.setSellerId("4");
        sellerInfo.setUsername("duan");
        sellerInfo.setPassword("qqq");
        sellerInfoRepository.save(sellerInfo);
    }
}