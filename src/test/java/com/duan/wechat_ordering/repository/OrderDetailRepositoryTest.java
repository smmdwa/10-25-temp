package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    void findByOrderId() {
        List<OrderDetail>result= repository.findByOrderId("aa");
        for(OrderDetail orderDetail:result){
            System.out.println(orderDetail.toString());
        }
    }

    @Test
    void save(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("111");
        orderDetail.setOrderId("aaa");
        orderDetail.setProductIcon("1.jpg");
        orderDetail.setProductId("222");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(123));
        orderDetail.setProductQuantity(100);
        repository.save(orderDetail);

    }
}