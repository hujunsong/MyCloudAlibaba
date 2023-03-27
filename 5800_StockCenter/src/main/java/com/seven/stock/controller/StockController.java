package com.seven.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Value("${stockCount}")
    private Integer configValue;

    @ResponseBody
    @RequestMapping("/stock/get")
    public Integer getStock() {
        return configValue;
    }

    @ResponseBody
    @RequestMapping("/stock/lock")
    public int lockStock() {
        return configValue --;
    }
}