package com.duan.wechat_ordering.service;

public interface ConcurrentService {

     void buy(String productId);
     String query(String productId);
}
