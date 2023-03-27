package com.seven.account.dao;

import com.seven.account.entity.ShopAccountDetailEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 账户明细表(ShopAccountDetail)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-26 10:38:15
 */
public interface ShopAccountDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userNo 主键
     * @return 实例对象
     */
    ShopAccountDetailEntity queryByUserNo(@Param("userNo") String userNo);

    /**
     * 通过ID查询单条数据
     *
     * @param orderNo 主键
     * @return 实例对象
     */
    ShopAccountDetailEntity queryByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 新增数据
     *
     * @param shopAccountDetail 实例对象
     * @return 影响行数
     */
    int insertAccountDetail(ShopAccountDetailEntity shopAccountDetail);
}

