package com.example;

import com.maihaoche.starter.mq.annotation.EnableMQConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuming
 * @date 2019/3/23
 */
@SpringBootApplication
@EnableMQConfiguration
@MapperScan(basePackages = "com.example.dao")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
