package com.sara.stock.service;

import com.sara.stock.dto.ShopStockOptDetailDto;
import com.sara.stock.entity.ShopStockEntity;

import java.util.List;

/**
 * 库存表(ShopStock)表服务接口
 *
 * @author hujunsong
 * @since 2023-03-27 11:37:05
 */
public interface ShopStockService {

    /**
     * 创建库存
     *
     * @param skuNo 物料代码
     * @return
     */
    ShopStockEntity createStock(String skuNo);

    /**
     * 通过ID查询单条数据
     *
     * @param skuNo 主键
     * @return 实例对象
     */
    ShopStockEntity queryBySkuNo(String skuNo);

    /**
     * 添加库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    ShopStockEntity addStock(String skuNo, Integer numsOpt, String orderNo);

    /**
     * 批量添加库存
     *
     * @param shopStockLockDtoList
     * @param orderNo
     * @return : com.sara.stock.entity.ShopStockEntity
     * @author: hujunsong
     * @date: 2023/3/30 23:17
     */
    void addStockBatch(List<ShopStockOptDetailDto> shopStockLockDtoList, String orderNo);

    /**
     * 冻结库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    ShopStockEntity lockStock(String skuNo, Integer numsOpt, String orderNo);

    /**
     * 批量冻结库存
     *
     * @param shopStockLockDtoList
     * @param orderNo
     * @return : void
     * @author: hujunsong
     * @date: 2023/3/31 08:39
     */
    void batchLockStock(List<ShopStockOptDetailDto> shopStockLockDtoList, String orderNo);

    /**
     * 解冻库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    ShopStockEntity unlockStock(String skuNo, Integer numsOpt, String orderNo);

    /**
     * 批量解冻库存
     *
     * @param shopStockLockDtoList
     * @param orderNo
     * @return : void
     * @author: hujunsong
     * @date: 2023/3/31 08:43
     */
    void batchUnlockStock(List<ShopStockOptDetailDto> shopStockLockDtoList, String orderNo);

    /**
     * 减少库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    ShopStockEntity reduceStock(String skuNo, Integer numsOpt, String orderNo);

    /**
     * 批量减少库存
     *
     * @param shopStockLockDtoList
     * @param orderNo
     * @return : void
     * @author: hujunsong
     * @date: 2023/3/31 08:45
     */
    void batchReduceStock(List<ShopStockOptDetailDto> shopStockLockDtoList, String orderNo);
}
