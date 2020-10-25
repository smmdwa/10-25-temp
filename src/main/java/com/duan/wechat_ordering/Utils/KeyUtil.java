package com.duan.wechat_ordering.Utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一的主键
     * 原理：随机数+时间
     * 注意：  添加了synchronized -----  防止并发同时访问
     * @return
     */
    public static synchronized String getKey(){
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }

//    public static voi d main(String[] args) {
//        System.out.println(KeyUtil.getKey());
//        System.out.println(System.currentTimeMillis());
//    }
}
