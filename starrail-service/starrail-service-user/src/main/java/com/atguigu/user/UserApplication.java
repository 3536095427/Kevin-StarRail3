package com.atguigu.user;


import com.atguigu.log.annoation.EnableLogAspect;
import com.atguigu.commonservice.annotation.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableUserWebMvcConfiguration
@EnableLogAspect
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
