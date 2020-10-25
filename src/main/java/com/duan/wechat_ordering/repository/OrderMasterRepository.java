package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    @Query(nativeQuery = true,value = "select * from order_master o where o.buyer_openid=?1 ")
    Page<OrderMaster> findByBuyerOpenid(String BuyerOpenid , Pageable pageable);

    @Query(nativeQuery = true,value = "select *from order_master o where o.order_id=?1")
    OrderMaster findOne(String orderId);
}
