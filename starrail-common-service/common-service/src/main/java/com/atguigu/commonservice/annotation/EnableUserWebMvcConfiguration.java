package com.atguigu.commonservice.annotation;




// 自定义注解
// 由于SpringBoot的自动装配机制，只会扫描和启动类在
// 同一级目录下的类，为了保证拦截器配置可以正常生效（即
// 确保配置类和拦截器类可以被注入容器），采取自定义注解的
// 方式，通过注解完成注入。

import com.atguigu.commonservice.config.UserWebMvcConfig;
import com.atguigu.commonservice.interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 需要在启动类上添加@EnableUserWebMvcConfiguration注解
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Import({UserWebMvcConfig.class, UserLoginAuthInterceptor.class})
public @interface EnableUserWebMvcConfiguration {
}
