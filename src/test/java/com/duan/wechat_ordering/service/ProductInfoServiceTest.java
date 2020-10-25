package com.duan.wechat_ordering.service;

import com.duan.wechat_ordering.dto.CartDTO;
import com.duan.wechat_ordering.service.impl.ProductInfoImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoServiceTest {
    @Autowired
    private ProductInfoImpl productInfo;

    @Test
    void findOne() {
    }

    @Test
    void findUpAll() {
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void increaseStock() {
    }

    @Test
    void decreaseStock() {
        List<CartDTO> cartDTOList=new ArrayList<>();
        CartDTO cartDTO=new CartDTO("123",1);
        cartDTOList.add(cartDTO);
        productInfo.decreaseStock(cartDTOList);
    }
}