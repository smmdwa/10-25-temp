package com.duan.wechat_ordering.form;

import lombok.Data;

@Data
public class CategoryForm {
    private Integer CategoryId;

    /**
     * 种类的名字
     */
    private  String CategoryName;

    /**
     * 种类的类型
     */
    private Integer CategoryType;
}
