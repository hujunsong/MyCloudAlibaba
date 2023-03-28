package com.seven.stock.controller;

import com.sara.utils.response.CommonResult;
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
     * 创建库存
     *
     * @param skuNo 物料代码
     * @return 库存信息
     */
    @ResponseBody
    @PostMapping("/stock/create")
    public CommonResult<ShopStockEntity> createStock(String skuNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.createStock(skuNo);
            return new CommonResult<ShopStockEntity>().success(shopStockEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopStockEntity>().fail();
        }
    }

    /**
     * 增加库存
     *
     * @param skuNo   物料代码
     * @param nums    增加库存数量
     * @param orderNo 订单号
     * @return
     */
    @ResponseBody
    @PostMapping("/stock/add")
    public CommonResult<ShopStockEntity> addStock(String skuNo, Integer nums, String orderNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.addStock(skuNo, nums, orderNo);
            return new CommonResult<ShopStockEntity>().success(shopStockEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopStockEntity>().fail();
        }
    }

    /**
     * 查询库存
     *
     * @param skuNo 物料代码
     * @return 库存信息
     */
    @ResponseBody
    @PostMapping("/stock/get")
    public CommonResult<ShopStockEntity> getStock(String skuNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.queryBySkuNo(skuNo);
            return new CommonResult<ShopStockEntity>().success(shopStockEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopStockEntity>().fail();
        }
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
    public CommonResult<ShopStockEntity> lockStock(String skuNo, Integer num, String orderNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.lockStock(skuNo, num, orderNo);
            return new CommonResult<ShopStockEntity>().success(shopStockEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopStockEntity>().fail();
        }
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
    public CommonResult<ShopStockEntity> unlockStock(String skuNo, Integer num, String orderNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.unlockStock(skuNo, num, orderNo);
            return new CommonResult<ShopStockEntity>().success(shopStockEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopStockEntity>().fail();
        }
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
    public CommonResult<ShopStockEntity> reduceStock(String skuNo, Integer num, String orderNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.reduceStock(skuNo, num, orderNo);
            return new CommonResult<ShopStockEntity>().success(shopStockEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopStockEntity>().fail();
        }
    }
}