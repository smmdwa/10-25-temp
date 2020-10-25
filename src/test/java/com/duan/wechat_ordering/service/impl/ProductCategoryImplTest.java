package com.duan.wechat_ordering.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCategoryImplTest {

    @Autowired
    private  ProductCategoryImpl productCategoryimpl;

    @Test
    void findOne() {
        System.out.println(productCategoryimpl.findOne(3).toString());
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void findByCategoryTypeIn() {
    }
}