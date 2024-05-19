package com.atguigu.travel;


import com.atguigu.commonservice.annotation.EnableUserTokenFeignInterceptor;
import com.atguigu.commonservice.annotation.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableUserWebMvcConfiguration
@EnableUserTokenFeignInterceptor
@EnableFeignClients(basePackages = "com.atguigu.feign.ticket")
@EnableCaching
public class TravelPathService {
    public static void main(String[] args) {
        SpringApplication.run(TravelPathService.class);
    }
}
