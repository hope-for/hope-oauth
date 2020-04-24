package com.hope.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hope.oauth.mapper")
public class Application{ // WebMvcConfigurationSupport

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("========》认证授权服务启动成功《========");
    }
}
