package com.seven.stock.dao;

import com.seven.stock.entity.ShopStockEntity;
import feign.Param;

/**
 * 库存表(ShopStock)表数据库访问层
 *
 * @author hujunsong
 * @since 2023-03-27 11:36:57
 */
public interface ShopStockDao {

    /**
     * 通过skuNo查询单条数据
     *
     * @param skuNo 主键
     * @return 实例对象
     */
    ShopStockEntity queryBySkuNo(@Param("skuNo") String skuNo);

    int createStock(@Param("id") Long id, @Param("skuNo") String skuNo);

    int addStock(@Param("skuNo") String skuNo, @Param("numOpt") Integer numOpt);

    int lockStock(@Param("skuNo") String skuNo, @Param("numOpt") Integer numOpt);

    int unlockStock(@Param("skuNo") String skuNo, @Param("numOpt") Integer numOpt);

    int reduceStock(@Param("skuNo") String skuNo, @Param("numOpt") Integer numOpt);
}