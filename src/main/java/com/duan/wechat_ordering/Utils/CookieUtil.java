package com.duan.wechat_ordering.Utils;

import org.springframework.data.domain.PageRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    public static void setCookie(HttpServletResponse response,
                                 String name,
                                 String value,
                                 int maxAge){
        Cookie cookie=new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    public static Cookie getCookie(HttpServletRequest request,
                                   String name){
        Map<String,Cookie> cookieMap=readCookie(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        else{
            return null;
        }
    }

    public static Map<String, Cookie> readCookie(HttpServletRequest request){
        Map<String,Cookie>cookieMap=new HashMap<>();
        Cookie[]cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
