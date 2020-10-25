package com.duan.wechat_ordering.Controller;



import com.duan.wechat_ordering.service.ConcurrentService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concurrent")
public class Concurrent {
    @Autowired
    private ConcurrentService concurrentService;
    @GetMapping("/buy")
    public void buy(){
        String productId="123456";
        concurrentService.buy(productId);
    }
    @GetMapping("/query")
    public String query(){
        String productId="123456";
        return concurrentService.query(productId);
    }
}
