package com.seven.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("com.seven.account.dao")
public class AccountCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountCenterApplication.class, args);
    }
}