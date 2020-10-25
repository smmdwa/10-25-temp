package com.duan.wechat_ordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WechatOrderingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatOrderingApplication.class, args);
    }

}
