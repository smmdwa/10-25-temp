package com.duan.wechat_ordering.handler;


import com.duan.wechat_ordering.config.ProjectUrlConfig;
import com.duan.wechat_ordering.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handler(){
        return new ModelAndView("redirect:"+
                projectUrlConfig.getSell()+
                "/sell/seller/templogin");
    }

}
