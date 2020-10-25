package com.duan.wechat_ordering.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 类名  entity
 *
 */

@Entity
@DynamicUpdate  //加了这个才能让Updatetime字段自动更新
@Data
public class ProductCategory {
    /**
     * 种类的ID
     */
    @Id
    @GeneratedValue
    private Integer CategoryId;

    /**
     * 种类的名字
     */
    private  String CategoryName;

    /**
     * 种类的类型
     */
    private Integer CategoryType;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "CategoryId=" + CategoryId +
                ", CategoryName='" + CategoryName + '\'' +
                ", CategoryType=" + CategoryType +
                '}';
    }
    public ProductCategory(String CategoryName,Integer CategoryType){

        this.CategoryName=CategoryName;
        this.CategoryType=CategoryType;
    }
    public ProductCategory() {
    }
}
