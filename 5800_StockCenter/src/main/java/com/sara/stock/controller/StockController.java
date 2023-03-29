package com.sara.stock.controller;

import com.sara.stock.dto.ShopStockDto;
import com.sara.stock.entity.ShopStockEntity;
import com.sara.stock.service.ShopStockService;
import com.sara.stock.utils.PojoConverter;
import com.sara.utils.response.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 库存接口
 */
@RestController
public class StockController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ShopStockService shopStockService;

    /**
     * 创建库存
     *
     * @param skuNo 物料代码
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:51
     */
    @ResponseBody
    @PostMapping("/stock/create")
    public CommonResult<ShopStockDto> createStock(String skuNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.createStock(skuNo);
            return new CommonResult<ShopStockDto>()
                    .success(PojoConverter.ShopStockEntity2Dto(shopStockEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopStockDto>().fail(exception.getMessage());
        }
    }

    /**
     * 增加库存
     *
     * @param skuNo   物料代码
     * @param nums    增加库存数量
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:52
     */
    @ResponseBody
    @PostMapping("/stock/add")
    public CommonResult<ShopStockDto> addStock(String skuNo, Integer nums, String orderNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.addStock(skuNo, nums, orderNo);
            return new CommonResult<ShopStockDto>().success(PojoConverter.ShopStockEntity2Dto(shopStockEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopStockDto>().fail(exception.getMessage());
        }
    }

    /**
     * 查询库存
     *
     * @param skuNo 物料代码
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:52
     */
    @ResponseBody
    @PostMapping("/stock/get")
    public CommonResult<ShopStockDto> getStock(String skuNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.queryBySkuNo(skuNo);
            return new CommonResult<ShopStockDto>().success(PojoConverter.ShopStockEntity2Dto(shopStockEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopStockDto>().fail(exception.getMessage());
        }
    }

    /**
     * 锁定库存
     *
     * @param skuNo   物料代码
     * @param num     数量
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:53
     */
    @ResponseBody
    @PostMapping("/stock/lock")
    public CommonResult<ShopStockDto> lockStock(String skuNo, Integer num, String orderNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.lockStock(skuNo, num, orderNo);
            return new CommonResult<ShopStockDto>().success(PojoConverter.ShopStockEntity2Dto(shopStockEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopStockDto>().fail(exception.getMessage());
        }
    }

    /**
     * 解锁库存
     *
     * @param skuNo   物料代码
     * @param num     数量
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:53
     */
    @ResponseBody
    @PostMapping("/stock/unlock")
    public CommonResult<ShopStockDto> unlockStock(String skuNo, Integer num, String orderNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.unlockStock(skuNo, num, orderNo);
            return new CommonResult<ShopStockDto>().success(PojoConverter.ShopStockEntity2Dto(shopStockEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopStockDto>().fail();
        }
    }

    /**
     * 减少库存
     *
     * @param skuNo   物料代码
     * @param num     数量
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:54
     */
    @ResponseBody
    @PostMapping("/stock/reduce")
    public CommonResult<ShopStockDto> reduceStock(String skuNo, Integer num, String orderNo) {
        try {
            ShopStockEntity shopStockEntity = shopStockService.reduceStock(skuNo, num, orderNo);
            return new CommonResult<ShopStockDto>().success(PojoConverter.ShopStockEntity2Dto(shopStockEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopStockDto>().fail();
        }
    }
}