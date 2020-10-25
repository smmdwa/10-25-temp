package com.duan.wechat_ordering.aspect;


import com.duan.wechat_ordering.Utils.CookieUtil;
import com.duan.wechat_ordering.constants.CookieConstant;
import com.duan.wechat_ordering.constants.RedisConstant;
import com.duan.wechat_ordering.enums.ResultEnum;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.exception.SellerAuthorizeException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * com.duan.wechat_ordering.Controller.Seller*.*(..))"+
    "&&!execution(public * com.duan.wechat_ordering.Controller.SellerUserController.*(..))")
    public void verify(){

    }

    @Before("verify()")
    public void doverify(){
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        //查询cookie
        Cookie cookie= CookieUtil.getCookie(request, CookieConstant.TOKEN);
        if(cookie==null){
            //没查到cookie
            throw new SellerAuthorizeException();

        }
        //去redis里面查询
        String tokenValue=stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            //没查到该token
            throw new SellerAuthorizeException();
        }

    }

}
