package com.seven.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.sara","com.seven"})
public class StockCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockCenterApplication.class, args);
    }
}