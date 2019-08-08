package com.feeling.tsukkomi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.moses.springbootdatajdbc.mapper")
@SpringBootApplication
public class SpringBootDataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJdbcApplication.class, args);
    }

}
