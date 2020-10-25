package com.duan.wechat_ordering.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * @param key 这里的Key是代表ProductId  对某个产品进行上锁
     * @param value  这里的value是当前时间+超时时间  也就是在规定的时间内，这把锁才有用，不然的话就失活
     * @return
     */
    public boolean lock(String key,String value){
        //这段的意思是，如果这把锁没人用，那就直接拿走 返回true
        //setifabsent 就是如果为空就set 如果有值就当无事发生过
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        //如果锁有人用了，就要判断这个锁是不是超时了，超时了就可以把这个锁拿过来 返回true

        //经典例子就是，现在两个线程同时进来
        // 两个线程的 key是123 value是100秒  currentValue是95秒 95<100 代表已经超时了
        //也就是某一个线程能拿到这个锁，而另一个线程拿不到这个锁。
        String currentValue=redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue)
                &&Long.parseLong(currentValue)<System.currentTimeMillis()){
            //进入这里代表超时了 可以拿锁
            //对第一个线程  oldValue 值为95 也就是currentValue
            //对第二个线程  oldValue 值为100 也就是第一个线程set的value
            String oldValue=redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue)
                    &&oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;

    }


    public void unlock(String key,String value){
        String currentValue=redisTemplate.opsForValue().get(key);
        try {
            if(StringUtils.isEmpty(currentValue)&&currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            System.out.println("redis解锁异常！");
        }
    }
}
