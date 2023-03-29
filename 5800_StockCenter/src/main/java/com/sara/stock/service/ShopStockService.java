package com.sara.stock.service;

import com.sara.stock.entity.ShopStockEntity;

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
     * 冻结库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    ShopStockEntity lockStock(String skuNo, Integer numsOpt, String orderNo);

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
     * 减少库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    ShopStockEntity reduceStock(String skuNo, Integer numsOpt, String orderNo);
}
