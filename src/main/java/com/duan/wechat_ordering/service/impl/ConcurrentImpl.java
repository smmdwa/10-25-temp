package com.duan.wechat_ordering.service.impl;

import com.duan.wechat_ordering.Utils.KeyUtil;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.service.ConcurrentService;
import com.duan.wechat_ordering.service.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConcurrentImpl implements ConcurrentService {
    @Autowired
    private RedisLock redisLock;
    static private Map<String,Integer> stocks=new HashMap<>();
    static private Map<String,Integer> products=new HashMap<>();
    static private Map<String,String>  orders=new HashMap<>();

    static {
        stocks.put("123456",10000);
        products.put("123456",10000);
    }

    @Override
    public  void buy(String productId) {
        //redis 分布式锁 加锁
        //设置超时时间为10秒 也就是当前时间+超时时间
        long time=10*1000+System.currentTimeMillis();
        if(!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101,"人也太多了把！稍后试试！");
        }

        //1.首先查询商品的库存还有没有
        int stock=stocks.get(productId);
        if(stock==0){
            return;
        }

        //2.下单
        orders.put(KeyUtil.getKey(),productId);
        stock=stock-1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //3.减库存
        stocks.put(productId,stock);

        //redis 分布式锁 解锁
        redisLock.unlock(productId,String.valueOf(time));

    }

    @Override
    public String query(String productId) {
        return "国庆特价，商品共限量："+products.get(productId)+"份,"+"现在还剩："
                +stocks.get(productId)+"份。 该商品成功下单的用户数为："
                +orders.size();
    }
}
