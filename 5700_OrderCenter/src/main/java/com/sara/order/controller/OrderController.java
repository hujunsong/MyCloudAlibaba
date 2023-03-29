package com.sara.order.controller;

import com.sara.stock.func.StockInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private StockInterface stockInterface;

    @ResponseBody
    @RequestMapping("/order/get")
    public String getOrder() {
        return null;
    }

    @ResponseBody
    @RequestMapping("/order/apply")
    public String applyOrder(String skuNo, int nums) {
        return null;
    }
}