package com.duan.wechat_ordering.Controller;


import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.entity.OrderDetail;
import com.duan.wechat_ordering.enums.ResultEnum;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/lists")
        public ModelAndView findList(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "size",defaultValue = "2")Integer size,
                                 Map<String ,Object> map){
        PageRequest pageRequest=PageRequest.of(page-1,size);  //从第一页开始
        Page<OrderDTO> orderDTOPage=orderService.findList(pageRequest);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        ModelAndView modelAndView=new ModelAndView("/order/lists",map);
        return modelAndView;
    }

    @GetMapping("/cancel")
    public ModelAndView  cancel(@RequestParam("orderId")String orderId,
                                Map<String ,Object> map){
        /**捕获这个异常  不然的话 网页那边就会报500 然后后端显示sellexception */

        try {
            OrderDTO orderDTO=orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException  e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/lists");
            return new ModelAndView("common/fail",map);
        }
        map.put("msg","取消订单成功！");
        map.put("url","/sell/seller/order/lists");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,
                               Map<String ,Object> map){
        try {
            OrderDTO orderDTO=orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException  e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/lists");
            return new ModelAndView("common/fail",map);
        }
        map.put("msg","完结订单成功！" );
        map.put("url","/sell/seller/order/lists");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/details")
    public ModelAndView detail(@RequestParam("orderId")String orderId,
                               Map<String ,Object> map){
        OrderDTO orderDTO=orderService.findOne(orderId);
        map.put("orderDTO",orderDTO);
        map.put("orderDetailList",orderDTO.getOrderDetailList());
        return new ModelAndView("/order/details",map);
    }
}
