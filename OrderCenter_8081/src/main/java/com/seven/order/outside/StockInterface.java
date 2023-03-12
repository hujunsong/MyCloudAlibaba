package com.seven.order.outside;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * nacos 服务 id
 */
@FeignClient("StockCenter")
public interface StockInterface {

    /**
     * 使用 get 方式，调用服务提供者的 /stock/lock 接口
     * @return
     */
    @GetMapping("/stock/lock")
    String lockStock();
}