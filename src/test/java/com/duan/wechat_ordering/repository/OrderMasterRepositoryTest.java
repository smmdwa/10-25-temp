package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    void findByBuyerOpenid() {
        PageRequest request= PageRequest.of(0,2);
        Page<OrderMaster> result= repository.findByBuyerOpenid("123",request);
        System.out.println(result.getTotalElements() );
    }

    @Test
    void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerName("段英文");
        orderMaster.setBuyerOpenid("123");
        orderMaster.setBuyerPhone("132616");
        orderMaster.setOrderAmount(new BigDecimal(123));
        orderMaster.setOrderId("aaa22A");
        repository.save(orderMaster);

    }

}