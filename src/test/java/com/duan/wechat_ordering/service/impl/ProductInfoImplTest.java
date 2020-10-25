package com.duan.wechat_ordering.service.impl;

import com.duan.wechat_ordering.entity.ProductInfo;
import com.duan.wechat_ordering.repository.ProductInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoImplTest {
    @Autowired
    private ProductInfoImpl productInfo;

    @Test
    void findOne() {

    }

    @Test
    void findUpAll() {
        List<ProductInfo> productInfos=productInfo.findUpAll();
        for(ProductInfo productInfo:productInfos){
            System.out.println(productInfo.toString());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }
}