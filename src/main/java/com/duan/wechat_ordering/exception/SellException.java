package com.duan.wechat_ordering.exception;

import com.duan.wechat_ordering.enums.ResultEnum;
import org.springframework.data.relational.core.sql.In;

public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        //TODO  这里 为啥 看不懂啊——————————————————————————
        //猜想是只有exception才能throw 这里返回msg给上一级  让他们报错的时候输出
        /**--------------------*/
        super(resultEnum.getMsg());

        this.code=resultEnum.getCode();

    }

    public SellException(Integer code , String msg) {
        super(msg);
        this.code=code;

    }
}
