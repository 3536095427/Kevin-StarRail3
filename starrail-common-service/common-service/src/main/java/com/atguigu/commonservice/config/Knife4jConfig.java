package com.atguigu.commonservice.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    /***
     * @description 自定义接口信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("StarRail项目API接口文档")
                        .version("3.0")
                        .description("API接口文档")
                        .contact(new Contact().name("lisushang").email("3536095427@qq.com")));
        // 设定作者
    }

//    @Bean
//    public GroupedOpenApi adminApi() {
//        // 创建了一个api接口的分组
//        // 分组名称
//        // 接口请求路径规则
//        return GroupedOpenApi.builder()
//                .group("admin-api")
//                .pathsToMatch("/admin/**")
//                .build();
//    }


    @Bean
    public GroupedOpenApi webApi() {
        // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .packagesToScan("com.atguigu.controller")
                .group("web-api")
                .pathsToMatch("/api/**")
                .build();
    }

}