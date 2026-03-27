package com.fxt.newgb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fxt.newgb.mapper.*")
public class NewgbServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(NewgbServiceApplication.class, args);
    }
}
