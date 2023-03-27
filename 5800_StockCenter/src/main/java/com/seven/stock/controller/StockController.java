package com.seven.stock.controller;

import com.seven.stock.entity.ShopStockEntity;
import com.seven.stock.service.ShopStockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 库存接口
 */
@RestController
public class StockController {

    @Resource
    private ShopStockService shopStockService;

    /**
     * 创建sku库存信息
     *
     * @param skuNo 物料代码
     * @return 库存信息
     */
    @ResponseBody
    @PostMapping("/stock/create")
    public ShopStockEntity createStock(String skuNo) {
        return shopStockService.createStock(skuNo);
    }

    /**
     * 获取sku库存信息
     *
     * @param skuNo 物料代码
     * @return 库存信息
     */
    @ResponseBody
    @PostMapping("/stock/get")
    public ShopStockEntity getStock(String skuNo) {
        ShopStockEntity shopStockEntity = shopStockService.queryBySkuNo(skuNo);
        return shopStockEntity;
    }

    /**
     * 锁定库存
     *
     * @param skuNo   物料代码
     * @param num     数量
     * @param orderNo 订单号
     * @return
     */
    @ResponseBody
    @PostMapping("/stock/lock")
    public ShopStockEntity lockStock(String skuNo, Integer num, String orderNo) {
        return shopStockService.lockStock(skuNo, num, orderNo);
    }

    /**
     * 解锁库存
     *
     * @param skuNo   物料代码
     * @param num     数量
     * @param orderNo 订单号
     * @return
     */
    @ResponseBody
    @PostMapping("/stock/unlock")
    public ShopStockEntity unlockStock(String skuNo, Integer num, String orderNo) {
        return shopStockService.unlockStock(skuNo, num, orderNo);
    }

    /**
     * 减少库存
     *
     * @param skuNo   物料代码
     * @param num     数量
     * @param orderNo 订单号
     * @return
     */
    @ResponseBody
    @PostMapping("/stock/reduce")
    public ShopStockEntity reduceStock(String skuNo, Integer num, String orderNo) {
        return shopStockService.reduceStock(skuNo, num, orderNo);
    }
}