package com.duan.wechat_ordering.entity;


import com.duan.wechat_ordering.enums.OrderStatusEnum;
import com.duan.wechat_ordering.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {


    @Id
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

    /** 创建时间**/
    private Date CreateTime;

    /** 更新时间**/
    private Date UpdateTime;
}
