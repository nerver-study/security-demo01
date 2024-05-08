package com.lzw.auth.securitydemo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lzw.auth.securityDemo01.mapper")
public class SecurityDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemo01Application.class, args);
    }

}
