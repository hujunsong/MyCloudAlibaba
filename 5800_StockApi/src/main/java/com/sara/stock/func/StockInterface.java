package com.sara.stock.func;

import com.sara.stock.dto.ShopStockDto;
import com.sara.utils.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description: 库存接口
 * @Author: hujunsong
 * @Date: 2023/3/29 16:08
 */
@FeignClient(name = "StockCenter")
public interface StockInterface {

    /**
     * @Description: 创建库存
     * @Author: hujunsong
     * @Date: 2023/3/29 15:52
     * @Param [skuNo] 物料代码
     * @Return com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @Exception
     */
    @PostMapping("/stock/create")
    CommonResult<ShopStockDto> createStock(String skuNo);

    /**
     * @param skuNo   物料代码
     * @param nums    增加库存数量
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @Description: 增加库存
     * @Exception
     * @Author: hujunsong
     * @Date: 2023/3/29 16:07
     */
    @PostMapping("/stock/add")
    CommonResult<ShopStockDto> addStock(String skuNo, Integer nums, String orderNo);

    /**
     * @param skuNo 物料代码
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @Description: 查询库存
     * @Exception
     * @Author: hujunsong
     * @Date: 2023/3/29 16:07
     */
    @PostMapping("/stock/get")
    CommonResult<ShopStockDto> getStock(String skuNo);

    /**
     * <pre>
     * @description: 锁定库存
     * @param skuNo 物料代码
     * @param num 数量
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:19
     * </pre>
     */
    @PostMapping("/stock/lock")
    CommonResult<ShopStockDto> lockStock(String skuNo, Integer num, String orderNo);

    /**
     * <pre>
     * @description: 解锁库存
     * @param skuNo 物料代码
     * @param num 数量
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:19
     * </pre>
     */
    @PostMapping("/stock/unlock")
    CommonResult<ShopStockDto> unlockStock(String skuNo, Integer num, String orderNo);

    /**
     * <pre>
     * @description: 减少库存
     * @param skuNo 物料代码
     * @param num 数量
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.stock.dto.ShopStockDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:21
     * </pre>
     */
    @PostMapping("/stock/reduce")
    CommonResult<ShopStockDto> reduceStock(String skuNo, Integer num, String orderNo);
}