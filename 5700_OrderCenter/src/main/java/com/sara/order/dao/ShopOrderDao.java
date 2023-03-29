package com.sara.order.dao;

import com.sara.order.entity.ShopOrder;
import org.apache.ibatis.annotations.Param;

/**
 * 订单表(ShopOrder)表数据库访问层
 *
 * @author hujunsong
 * @since 2023-03-28 16:54:47
 */
public interface ShopOrderDao {

    /**
     * 查询单条数据
     *
     * @param orderNo
     * @return 实例对象
     */
    ShopOrder queryByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 新增数据
     *
     * @param shopOrder 实例对象
     * @return 影响行数
     */
    int insert(ShopOrder shopOrder);
}

