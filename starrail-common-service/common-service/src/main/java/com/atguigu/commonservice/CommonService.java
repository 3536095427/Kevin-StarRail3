package com.atguigu.commonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CommonService {
    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(CommonService.class);
        String[] names = run.getBeanDefinitionNames();
        for (String name : names){
            System.out.println(name + run.getBean(name).getClass());
        }

    }
}
