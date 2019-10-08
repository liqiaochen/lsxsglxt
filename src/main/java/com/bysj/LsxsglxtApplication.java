package com.bysj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.bysj.lsxsglxt.mapper")
public class LsxsglxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsxsglxtApplication.class, args);
    }

}
