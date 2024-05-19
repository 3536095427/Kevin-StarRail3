package com.atguigu.acc;


import com.alibaba.fastjson.JSON;
import com.atguigu.model.common.Result;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;


@SpringBootTest
public class SecondTest {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testRedis(){
        System.out.println(redisTemplate);
        String name = redisTemplate.opsForValue().get("北京哈尔滨2024-03-24");

        Result result = JSON.parseObject(name, Result.class);
        System.out.println(result);
        System.out.println(name);
    }
}

