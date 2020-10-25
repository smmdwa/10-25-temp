package com.duan.wechat_ordering.entity;


import com.duan.wechat_ordering.Utils.EnumUtil;
import com.duan.wechat_ordering.enums.ProductStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInfo {

    /** 商品名字*/
    @Id
    private String ProductId;

    /** 商品名字 */
    private String ProductName;

    /** 商品价格*/
    private BigDecimal ProductPrice;

    /** 商品库存*/
    private Integer ProductStock;

    /** 商品描述*/
    private String ProductDescription;

    /** 商品小图 图标*/
    private String ProductIcon;

    /** 商品状态  0 正常  1下架   */
    private Integer ProductStatus;

    /** 类目编号  */
    private Integer CategoryType;

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
        ProductId = productId;
        ProductName = productName;
        ProductPrice = productPrice;
        ProductStock = productStock;
        ProductDescription = productDescription;
        ProductIcon = productIcon;
        ProductStatus = productStatus;
        CategoryType = categoryType;
    }

    public ProductInfo(){

    }

    @JsonIgnore
    public ProductStatusEnum getProductInfoStatusEnum(Integer code){
        return EnumUtil.getByCode(code,ProductStatusEnum.class );
    }
}
