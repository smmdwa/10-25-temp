package com.duan.wechat_ordering.service;


import com.duan.wechat_ordering.dto.CartDTO;
import com.duan.wechat_ordering.entity.ProductInfo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String ProductId);


    /**
     * 查询所有上架的商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO >cartDTOList);


    //减库存

    void decreaseStock(List<CartDTO>cartDTOList);

    ProductInfo onSale(String ProductId);

    ProductInfo unSale(String ProductId);
}
