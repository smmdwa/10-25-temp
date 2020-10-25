package com.duan.wechat_ordering.converter;

import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.entity.OrderMaster;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderMaster2OrderDTO {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO=new OrderDTO()   ;
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO>convert(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList=new ArrayList<>();
        for(OrderMaster orderMaster:orderMasterList){
            OrderDTO orderDTO=OrderMaster2OrderDTO.convert(orderMaster);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

}
