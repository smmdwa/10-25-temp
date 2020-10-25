package com.duan.wechat_ordering.service;

import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.entity.OrderDetail;
import com.duan.wechat_ordering.enums.PayStatusEnum;
import com.duan.wechat_ordering.service.impl.OrderImpl;
import org.junit.Assert;
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
class OrderServiceTest {

    @Autowired
    private OrderImpl orderImpl;

    private final String ORDER_ID="1595501617601563603";

    @Test
    void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerAddress("江西");
        orderDTO.setBuyerName("段");
        orderDTO.setBuyerOpenid("1110");

        //购物车
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
        o1.setProductId("123");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result=orderImpl.create(orderDTO);
        System.out.println(result);

    }

    @Test
    void findOne() {
        OrderDTO result=orderImpl.findOne(ORDER_ID);
        System.out.println(result);
    }

    @Test
    void findList() {

    }

    @Test
    void cancel() {
        OrderDTO orderDTO=orderImpl.findOne(ORDER_ID);
        orderImpl.cancel(orderDTO);
    }

    @Test
    void finish() {
        OrderDTO orderDTO=orderImpl.findOne(ORDER_ID);
        orderImpl.finish(orderDTO);
    }

    @Test
    void paid() {
        OrderDTO orderDTO=orderImpl.findOne(ORDER_ID);
        OrderDTO result=orderImpl.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}