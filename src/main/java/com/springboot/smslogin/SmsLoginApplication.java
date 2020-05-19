package com.springboot.smslogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.smslogin.dao")
public class SmsLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsLoginApplication.class, args);
    }

}
