package com.duan.wechat_ordering.service.impl;

import com.duan.wechat_ordering.dto.CartDTO;
import com.duan.wechat_ordering.entity.ProductInfo;
import com.duan.wechat_ordering.enums.ProductStatusEnum;
import com.duan.wechat_ordering.enums.ResultEnum;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.repository.ProductInfoRepository;
import com.duan.wechat_ordering.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
//这里要加service  不然就会在后面 @Autowired ProductInfoIml pro的是时候报错，
// 说Could not autowire. No beans of 'xxxx' type found
public class ProductInfoImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String ProductId) {
        return repository.findById(ProductId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=repository.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=repository.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            //库存不足--- 减失败了
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_SOTCK_ERROR);
            }

            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }


    @Override
    public ProductInfo onSale(String ProductId) {
        ProductInfo productInfo=repository.findOne(ProductId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus()==ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);

    }

    @Override
    public ProductInfo unSale(String ProductId) {
        ProductInfo productInfo=repository.findOne(ProductId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatus()==ProductStatusEnum.DOWN.getCode()){
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
