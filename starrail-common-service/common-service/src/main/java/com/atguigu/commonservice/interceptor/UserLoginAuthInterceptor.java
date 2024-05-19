package com.atguigu.commonservice.interceptor;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.commonservice.utils.AuthContext;
import com.atguigu.model.pojo.UserBasic;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLoginAuthInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    // 负责根据请求中的token从redis中获取用户信息,
    // 并注入基于ThreadLocal的上下文环境中
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String userJsonInfo = redisTemplate.opsForValue().get("user:" + token);
        if (!StrUtil.isEmpty(userJsonInfo)){
            AuthContext.setAuthContext(JSON.parseObject(userJsonInfo, UserBasic.class));
        }

        return true;
    }
}
