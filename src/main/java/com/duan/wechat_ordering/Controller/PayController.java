package com.duan.wechat_ordering.Controller;


import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.enums.ResultEnum;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.service.OrderService;
import com.duan.wechat_ordering.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;
    @GetMapping("/create")
    public void create(@RequestParam("orderId")String orderId,
                       @RequestParam("returnUrl")String returnUrl){

        //1 查询订单
        OrderDTO orderDTO=orderService.findOne(orderId);
        if(orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        //发起支付
        payService.create(orderDTO);
    }
}
