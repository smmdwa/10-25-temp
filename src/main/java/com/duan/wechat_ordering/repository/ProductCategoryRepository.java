package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    @Query(value = "select * from product_category  p where p.category_type in ?1 ",nativeQuery = true)
    List<ProductCategory> findByCategoryTypeIn(List<Integer>CategoryTypeList);
}
