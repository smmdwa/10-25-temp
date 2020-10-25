package com.duan.wechat_ordering.converter;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.entity.OrderDetail;
import com.duan.wechat_ordering.enums.ResultEnum;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTO   {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson=new Gson() ;
        OrderDTO orderDTO=new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());;
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        String items=orderForm.getItems();
        //注意，这里发生了一个问题  后端定义的是ProductId  ProductQuantity
        //但是前端传过来的是productId productQuantity
        //所以我们统一替换一下
        items=items.replaceAll( "productId","ProductId");
        items=items.replaceAll("productQuantity","ProductQuantity");
        List<OrderDetail> orderDetailList=new ArrayList<>();

        try {
            orderDetailList =gson.fromJson(items,
                    new TypeToken<List<OrderDetail>>() {

                    }.getType());
        }catch (Exception e){
            System.out.println("对象转换错误");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }

//    public static void main(String[] args) {
//
//        List<OrderDetail> orderDetailList=new ArrayList<>();
//        Gson gson=new Gson() ;
//        try {
//            orderDetailList=gson.fromJson("[{ProductId:\"11\",ProductQuantity:2 }]",
//                    new TypeToken<List<OrderDetail>>() {
//
//                    }.getType());
//        }catch (Exception e){
//            System.out.println("对象转换错误");
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//        System.out.println(orderDetailList);
//    }
}
