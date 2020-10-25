package com.duan.wechat_ordering.service;

import com.duan.wechat_ordering.entity.ProductCategory;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService {

    ProductCategory findOne(Integer CategoryId);

    List<ProductCategory>   findAll();

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory>   findByCategoryTypeIn(List<Integer> CategoryTypeList);
}
