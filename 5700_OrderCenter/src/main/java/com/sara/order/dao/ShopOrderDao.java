package com.sara.order.dao;

import com.sara.order.entity.ShopOrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    ShopOrderEntity queryByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 查询用户订单
     *
     * @param userNo
     * @return 实例对象
     */
    List<ShopOrderEntity> queryByUserNo(@Param("userNo") String userNo);

    /**
     * 新增数据
     *
     * @param shopOrder 实例对象
     * @return 影响行数
     */
    int insert(ShopOrderEntity shopOrder);
}

