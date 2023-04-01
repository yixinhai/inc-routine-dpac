package com.xh.routine.dpac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.xh.routine.dpac.mapper")
public class DpacApplication {

    public static void main(String[] args) {
        SpringApplication.run(DpacApplication.class, args);
    }
}
