package com.duan.wechat_ordering.Controller;


import com.duan.wechat_ordering.Utils.ResultVOUtil;
import com.duan.wechat_ordering.VO.ResultVO;
import com.duan.wechat_ordering.converter.OrderForm2OrderDTO;
import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.enums.ResultEnum;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.form.OrderForm;
import com.duan.wechat_ordering.service.BuyerService;
import com.duan.wechat_ordering.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("创建订单参数不正确！！！！！！！"  );
            /**这里因为需要展示更多东西，比如具体是哪一个参数不正确 需要显示给前端看*/
            //throw new SellException(ResultEnum.PARAM_ERROR);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO= OrderForm2OrderDTO.convert(orderForm);
        //如果购物车为空的话
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new SellException(ResultEnum.CART_EMPTY_ERROR);
        }
        OrderDTO createResult=orderService.create(orderDTO);
         Map<String,String>map=new HashMap<>();
         map.put("OrderId",createResult.getOrderId());
         return ResultVOUtil.success(map);
    }

    //查询订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid")String openid,
                                         @RequestParam(value = "page",defaultValue = "0")Integer page,
                                         @RequestParam(value = "size",defaultValue = "10")Integer size){

        //首先判断openid是否为空
        if(StringUtils.isEmpty(openid)){
            System.out.println("openid为空！！！！！！！！！！！！！");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request= PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage=orderService.findList(openid,request);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO <OrderDTO> detail(@RequestParam("openid")String openid,
                                      @RequestParam("orderid")String orderid){
        //为了安全性，必须要设置只有本人才能够去访问对应的订单

       // OrderDTO orderDTO=orderService.findOne(orderid);
        OrderDTO orderDTO=buyerService.findOrderOne(openid,orderid);
        return ResultVOUtil.success(orderDTO);

    }

    //取消订单
     @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid")String openid,
                           @RequestParam("orderid")String orderid){
        //TODO
         //同上 判断是否是本人
//
//         OrderDTO orderDTO=orderService.findOne(orderid);
//         orderService.cancel(orderDTO);
         /**取消订单不用返回对象----------*/
          buyerService.cancelOrder(openid,orderid);
         return ResultVOUtil.success();
     }
}
