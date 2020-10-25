package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void FindOneTest(){
        System.out.println(repository.findById(1).toString());
    }

    @Test
    @Transactional  //javax.transactional
    public void SaveTest(){
        ProductCategory productCategory=new ProductCategory("零食",3);

        ProductCategory result= (ProductCategory) repository.save(productCategory);
        Assert.assertNotNull(null,result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list= Arrays.asList(1,2,3);

        List<ProductCategory> productCategories= repository.findByCategoryTypeIn(list);

        for(ProductCategory productCategory:productCategories){
            System.out.println(productCategory.toString());
        }

    }

    @Test
    public void findAllTest(){
        List<ProductCategory>   productCategories=repository.findAll();
        for(ProductCategory productCategory:productCategories){
            System.out.println(productCategory.toString());
        }
    }
}