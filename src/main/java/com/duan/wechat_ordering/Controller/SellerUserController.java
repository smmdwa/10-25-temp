package com.duan.wechat_ordering.Controller;


import com.duan.wechat_ordering.Utils.CookieUtil;
import com.duan.wechat_ordering.config.ProjectUrlConfig;
import com.duan.wechat_ordering.constants.CookieConstant;
import com.duan.wechat_ordering.constants.RedisConstant;
import com.duan.wechat_ordering.entity.SellerInfo;
import com.duan.wechat_ordering.enums.ResultEnum;
import com.duan.wechat_ordering.service.SellerInfoService;
import com.lly835.bestpay.rest.type.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

//@RestController
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openId")String openId,
                              HttpServletResponse response,
                              Map<String,Object>map){
        {
            System.out.println("login-------------------------------------");
            //1.openid和数据库里的匹配
            SellerInfo sellerInfo=sellerInfoService.findSellerInfoByOpenId(openId);
            if(sellerInfo==null){
                //如果不存在 那就报错
                map.put("msg", ResultEnum.LOGIN_SELLER_NOT_EXIT.getMsg() );
                map.put("url","/sell/seller/product/lists");
                System.out.println("gg");
                return new ModelAndView("/common/fail",map);
            }
            //2.设置token到redis
            String token= UUID.randomUUID().toString();
            Integer expire= RedisConstant.EXPIRE;
            /********  用String.format(a ,"段应文")   来格式化string a="请输入:%s" ********/
            stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),
                    openId, expire, TimeUnit.SECONDS);
            //3.设置token到cookie
            CookieUtil.setCookie(response, CookieConstant.TOKEN,token,expire);
        }
        System.out.println("url："+"redirect:"+ projectUrlConfig.getSell()+"/sell/seller/product/lists");
        return new ModelAndView("redirect:"+ projectUrlConfig.getSell()+"/sell/seller/product/lists");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response,
                               HttpServletRequest request,
                               Map<String ,Object>map){

        //1.查询request 中的cookie
        Cookie cookie=CookieUtil.getCookie(request,CookieConstant.TOKEN);
        if(cookie!=null){
            //2.清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));

            //3.清除cookie
            CookieUtil.setCookie(response,cookie.getName(),null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url","/sell/seller/product/lists");

        return new ModelAndView("/common/success",map);
    }
    @GetMapping("/templogin")
    public ModelAndView templogin(){
        return new ModelAndView("common/login");
    }
}
