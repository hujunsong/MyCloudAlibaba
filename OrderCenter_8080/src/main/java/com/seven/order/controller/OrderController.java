package com.seven.order.controller;

import com.seven.order.outside.StockInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    /**
     * 加载 openfeign client
     */
    @Resource
    private StockInterface stockInterface;

    @Value("${orderName}")
    private String configValue;

    @ResponseBody
    @RequestMapping("/order/get")
    public String getOrder() {
        return configValue;
    }

    @ResponseBody
    @RequestMapping("/order/apply")
    public String applyOrder(String skuNo,int nums) {
        return stockInterface.lockStock(skuNo,nums);
    }
}