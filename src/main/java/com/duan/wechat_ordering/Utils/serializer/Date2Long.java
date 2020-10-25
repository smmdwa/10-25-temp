package com.duan.wechat_ordering.Utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

public class Date2Long extends JsonSerializer<Date> {

/**
 *  用于把Date 创建时间 更新时间转化为Long
 *  因为Date是以毫秒为单位的，而后面是以秒  也就是多了000 现在需要去掉这三个0
 * */
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime()/1000);
    }
}
