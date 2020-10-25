package com.duan.wechat_ordering.service.impl;

import com.duan.wechat_ordering.entity.ProductCategory;
import com.duan.wechat_ordering.repository.ProductCategoryRepository;
import com.duan.wechat_ordering.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//这里要加service  不然就会在后面 @Autowired ProductInfoIml pro的是时候报错，
// 说Could not autowire. No beans of 'xxxx' type found
public class ProductCategoryImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer CategoryId) {
        return repository.findById(CategoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypeList) {
        return repository.findByCategoryTypeIn(CategoryTypeList);
    }
}
