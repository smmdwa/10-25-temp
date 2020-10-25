package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

      @Query(nativeQuery = true,value = "select *from product_info p where p.product_status=?1")
      List<ProductInfo> findByProductStatus(Integer ProductStatus);

      @Query(nativeQuery = true,value = "select * from product_info p where p.product_id=?1")
      ProductInfo findOne(String ProductId);
}
