package com.sara.stock.controller;

import com.sara.stock.dto.ShopStockDto;
import com.sara.stock.dto.ShopStockOptDto;
import com.sara.stock.entity.ShopStockEntity;
import com.sara.stock.func.StockInterface;
import com.sara.stock.service.ShopStockService;
import com.sara.stock.utils.PojoConverter;
import com.sara.utils.response.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 库存接口
 *
 * @author: hujunsong
 * @date: 2023/3/31 10:35
 */
@RestController
public class StockController implements StockInterface {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ShopStockService shopStockService;

    /**
     * 创建库存
     *
     * @param skuNo 物料代码
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/31 10:37
     */
    @Override
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
     * 查询库存
     *
     * @param skuNo 物料代码
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:52
     */
    @Override
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
     * 增加库存
     *
     * @param shopStockOptDto 操作明细
     * @return : com.sara.utils.response.CommonResult<java.lang.Void>
     * @author: hujunsong
     * @date: 2023/3/31 10:23
     */
    @PostMapping("/stock/add")
    public CommonResult<Void> addStock(@RequestBody @Valid ShopStockOptDto shopStockOptDto) {
        try {
            shopStockService.batchLockStock(shopStockOptDto.getShopStockOptDetailDtoList(), shopStockOptDto.getFlowNo());
            return new CommonResult().success();
        } catch (Exception exception) {
            return new CommonResult().fail(exception.getMessage());
        }
    }

    /**
     * 锁定库存
     *
     * @param shopStockOptDto 操作明细
     * @return : com.sara.utils.response.CommonResult
     * @author: hujunsong
     * @date: 2023/3/31 09:45
     */
    @Override
    @ResponseBody
    @PostMapping("/stock/lock")
    public CommonResult<Void> lockStock(@RequestBody @Valid ShopStockOptDto shopStockOptDto) {
        try {
            shopStockService.batchLockStock(shopStockOptDto.getShopStockOptDetailDtoList(), shopStockOptDto.getFlowNo());
            return new CommonResult().success(null);
        } catch (Exception exception) {
            return new CommonResult().fail(exception.getMessage());
        }
    }

    /**
     * 解锁库存
     *
     * @param shopStockOptDto
     * @return : com.sara.utils.response.CommonResult<java.lang.Void>
     * @author: hujunsong
     * @date: 2023/3/31 09:47
     */
    @Override
    @ResponseBody
    @PostMapping("/stock/unlock")
    public CommonResult<Void> unlockStock(@RequestBody @Valid ShopStockOptDto shopStockOptDto) {
        try {
            shopStockService.batchUnlockStock(shopStockOptDto.getShopStockOptDetailDtoList(), shopStockOptDto.getFlowNo());
            return new CommonResult<Void>().success();
        } catch (Exception exception) {
            return new CommonResult().fail(exception.getMessage());
        }
    }

    /**
     * 减少库存
     *
     * @param shopStockOptDto
     * @return : com.sara.utils.response.CommonResult<java.lang.Void>
     * @author: hujunsong
     * @date: 2023/3/31 10:31
     */
    @Override
    @ResponseBody
    @PostMapping("/stock/reduce")
    public CommonResult<Void> reduceStock(@RequestBody @Valid ShopStockOptDto shopStockOptDto) {
        try {
            shopStockService.batchReduceStock(shopStockOptDto.getShopStockOptDetailDtoList(), shopStockOptDto.getFlowNo());
            return new CommonResult().success();
        } catch (Exception exception) {
            return new CommonResult().fail(exception.getMessage());
        }
    }
}