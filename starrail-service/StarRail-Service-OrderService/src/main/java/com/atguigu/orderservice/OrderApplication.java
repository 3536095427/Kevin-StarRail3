package com.atguigu.orderservice;


import com.atguigu.commonservice.annotation.EnableUserTokenFeignInterceptor;
import com.atguigu.commonservice.annotation.EnableUserWebMvcConfiguration;
import com.atguigu.log.annoation.EnableLogAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableUserWebMvcConfiguration
@EnableFeignClients(basePackages = {"com.atguigu.feign.ticket"})
@EnableUserTokenFeignInterceptor
@EnableCaching
@EnableLogAspect
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }
}
