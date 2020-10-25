package com.duan.wechat_ordering.repository;

import com.duan.wechat_ordering.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;

import java.io.StringReader;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository <OrderDetail, String>{

    @Query(nativeQuery = true, value = "select * from order_detail o where o.order_id=?1")
    List<OrderDetail>  findByOrderId(String Orderid);
}
