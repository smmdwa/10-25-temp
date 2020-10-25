package com.duan.wechat_ordering.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
public class WeixinController {

    @GetMapping("/auth")
    public String auth(@RequestParam("code")String code){
        System.out.println("code="+code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbd05df8d85b776a4&secret=f5af6ba259287f8cd8c81c60ded18f07&code=061PG8S82i1iNL0VFtR82FRbS82PG8Sx&grant_type=authorization_code";
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject(url,String.class);
        System.out.println("response="+response);
        return "hello!";
    }

}
