package com.duan.wechat_ordering.converter;

import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.form.OrderForm;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderForm2OrderDTOTest {

    @Test
    void convert() {
        OrderForm orderForm=new OrderForm();
        orderForm.setName("111");
        orderForm.setItems("[{ProductId:'1',ProductQuantity:'2'}]");
        OrderDTO orderDTO=OrderForm2OrderDTO.convert(orderForm);
        System.out.println(orderDTO);
    }
}