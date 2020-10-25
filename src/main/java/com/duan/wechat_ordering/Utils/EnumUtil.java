package com.duan.wechat_ordering.Utils;

import com.duan.wechat_ordering.enums.CodeEnum;
import com.thoughtworks.xstream.core.util.Fields;

import java.util.LinkedList;
import java.util.List;

public class EnumUtil {
    public static <T extends CodeEnum>T getByCode(Integer code,Class<T> enumclass){



        for(T each:enumclass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }

}
