package com.atguigu.ticket;


import com.atguigu.commonservice.annotation.EnableUserTokenFeignInterceptor;
import com.atguigu.commonservice.annotation.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableUserTokenFeignInterceptor
@EnableFeignClients(basePackages = {"com.atguigu.feign.travelpath"})
@EnableUserWebMvcConfiguration
@EnableCaching
public class TicketApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketApplication.class);
    }
}
