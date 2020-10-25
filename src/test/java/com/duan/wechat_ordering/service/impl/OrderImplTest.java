package com.duan.wechat_ordering.service.impl;

import com.duan.wechat_ordering.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderImplTest {
    @Autowired
    private OrderImpl orderimpl;
    @Test
    public void findAllTest(){
        PageRequest pageRequest= PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage=orderimpl.findList(pageRequest);
        System.out.println(orderDTOPage);
    }
}