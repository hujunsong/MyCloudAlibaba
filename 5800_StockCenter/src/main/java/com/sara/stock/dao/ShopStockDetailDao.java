package com.sara.stock.dao;

import com.sara.stock.entity.ShopStockDetailEntity;

/**
 * 库存明细表(ShopStockDetail)表数据库访问层
 *
 * @author hujunsong
 * @since 2023-03-27 14:15:31
 */
public interface ShopStockDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param skuNo 主键
     * @return 实例对象
     */
    ShopStockDetailEntity queryBySkuNo(String skuNo);

    /**
     * 新增数据
     *
     * @param shopStockDetail 实例对象
     * @return 影响行数
     */
    int addStockDetail(ShopStockDetailEntity shopStockDetail);
}