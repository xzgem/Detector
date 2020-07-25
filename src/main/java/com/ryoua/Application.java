package com.ryoua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * * @Author: RyouA
 * * @Date: 2020/6/18
 **/
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.ryoua.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

