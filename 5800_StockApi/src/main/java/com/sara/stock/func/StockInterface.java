package com.sara.stock.func;

import com.sara.stock.dto.ShopStockDto;
import com.sara.stock.dto.ShopStockOptDto;
import com.sara.utils.response.CommonResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 库存接口
 * @Author: hujunsong
 * @Date: 2023/3/29 16:08
 */
@FeignClient(name = "StockCenter")
public interface StockInterface {

    /**
     * 创建库存
     *
     * @param skuNo 物料代码
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/31 10:22
     */
    @PostMapping("/stock/create")
    CommonResult<ShopStockDto> createStock(@RequestParam @NotBlank String skuNo);

    /**
     * 查询库存
     *
     * @param skuNo 物料代码
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/31 08:58
     */
    @PostMapping("/stock/get")
    CommonResult<ShopStockDto> getStock(@RequestParam @NotBlank String skuNo);

    /**
     * 增加库存
     *
     * @param shopStockOptDto 操作明细
     * @return : com.sara.utils.response.CommonResult<java.lang.Void>
     * @author: hujunsong
     * @date: 2023/3/31 10:23
     */
    @PostMapping("/stock/add")
    CommonResult<Void> addStock(@RequestBody @Valid ShopStockOptDto shopStockOptDto);

    /**
     * 锁定库存
     *
     * @param shopStockOptDto 操作明细
     * @return : com.sara.utils.response.CommonResult<java.lang.Void>
     * @author: hujunsong
     * @date: 2023/3/31 10:24
     */
    @PostMapping("/stock/lock")
    CommonResult<Void> lockStock(@RequestBody @Valid ShopStockOptDto shopStockOptDto);

    /**
     * 解锁库存
     *
     * @param shopStockOptDto 操作明细
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/31 10:25
     */
    @PostMapping("/stock/unlock")
    CommonResult<Void> unlockStock(@RequestBody @Valid ShopStockOptDto shopStockOptDto);

    /**
     * 减少库存
     *
     * @param shopStockOptDto 操作明细
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/31 10:25
     */
    @PostMapping("/stock/reduce")
    CommonResult<Void> reduceStock(@RequestBody @Valid ShopStockOptDto shopStockOptDto);

}