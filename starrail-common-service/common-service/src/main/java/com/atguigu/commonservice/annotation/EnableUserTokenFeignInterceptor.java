package com.atguigu.commonservice.annotation;


import com.atguigu.commonservice.interceptor.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Import({UserTokenFeignInterceptor.class})
public @interface EnableUserTokenFeignInterceptor {
}
