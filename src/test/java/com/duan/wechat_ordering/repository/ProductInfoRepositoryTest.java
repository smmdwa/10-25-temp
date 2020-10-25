package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.ProductInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;
    @Test
    void findByProductStatus() {
        List<ProductInfo> productInfos= repository.findByProductStatus(1);
        for(ProductInfo productInfo:productInfos){
            System.out.println(productInfo.toString());
        }
    }

    @Test
    void saveTest(){
        ProductInfo productInfo=new ProductInfo(
                "1234",
                "皮蛋粥",
                new BigDecimal(123),
                100,
                "皮蛋瘦肉粥",
                "https://111.jpg",
                0,
                1);

        repository.save(productInfo);

    }

}