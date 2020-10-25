package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
//     @Query(nativeQuery = true,value = "select *from seller_info p where p.openid=?1")
     SellerInfo findByOpenId(String openId);

     SellerInfo save(SellerInfo sellerInfo);
}
