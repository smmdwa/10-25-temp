package com.duan.wechat_ordering.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data

public class OrderDetail {

    @Id
    private String DetailId;

    private String ProductId;

    private String OrderId;

    private String ProductName;

    private BigDecimal ProductPrice;

    private Integer ProductQuantity;

    private String  ProductIcon;


}
