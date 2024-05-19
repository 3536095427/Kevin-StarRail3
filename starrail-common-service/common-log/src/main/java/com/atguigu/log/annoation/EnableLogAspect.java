package com.atguigu.log.annoation;


import com.atguigu.log.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// 通过Import注解导入日志切面类到Spring容器中
// 当启动类上方标识注解EnableLogAspect时，
// 会自动导入LogAspect类到Spring容器中
@Import(value = LogAspect.class)
public @interface EnableLogAspect {
}
