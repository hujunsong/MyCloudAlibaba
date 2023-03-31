package com.sara.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("com.sara.stock.dao")
@ComponentScan(basePackages = {"com.sara"})
public class StockCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockCenterApplication.class, args);
    }
}