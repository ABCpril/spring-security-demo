package com.sspath.multiusertablemybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sspath.multiusertablemybatis.mapper")
public class MultiUserTableMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiUserTableMybatisApplication.class, args);
    }

}
