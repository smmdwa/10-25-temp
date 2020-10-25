package com.duan.wechat_ordering.dto;


import com.duan.wechat_ordering.Utils.EnumUtil;
import com.duan.wechat_ordering.Utils.serializer.Date2Long;
import com.duan.wechat_ordering.entity.OrderDetail;
import com.duan.wechat_ordering.enums.OrderStatusEnum;
import com.duan.wechat_ordering.enums.PayStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO  {

    /** 不需要@ID注解   */
    private String OrderId;

    /**买家姓名*/
    private String BuyerName;

    /** 买家电话*/
    private String BuyerPhone;

    /** 买家地址*/
    private String BuyerAddress;

    /** 买家Openid **/
    private String BuyerOpenid;

    /** 订单总金额*/
    private BigDecimal OrderAmount;

    /** 订单状态 默认0为新下单 */
    private Integer OrderStatus= OrderStatusEnum.NEW.getCode();

    /** 支付状态 默认0 为未支付**/
    private Integer PayStatus= PayStatusEnum.WAIT.getCode();

    /** 创建时间  秒为单位* */
    @JsonSerialize(using = Date2Long.class)
    private Date CreateTime;

    /** 更新时间  秒为单位* */
    @JsonSerialize(using = Date2Long.class)
    private Date UpdateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(Integer code){
        return EnumUtil.getByCode(code,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(Integer code){
        return EnumUtil.getByCode(code,PayStatusEnum.class);
    }

}
