package com.seven.account.dao;

import com.seven.account.entity.ShopAccountEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 账户表(ShopAccount)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-26 08:22:29
 */
public interface ShopAccountDao {

    int createAccount(@Param("id") Long id, @Param("userNo") String userNo);

    /**
     * 通过ID查询单条数据
     *
     * @param userNo 主键
     * @return 实例对象
     */
    ShopAccountEntity queryByUserNo(@Param("userNo") String userNo);

    int addAmount(@Param("userNo") String userNo, @Param("amountOpt") BigDecimal amountOpt);

    int lockAmount(@Param("userNo") String userNo, @Param("amountOpt") BigDecimal amountOpt);

    int unlockAmount(@Param("userNo") String userNo, @Param("amountOpt") BigDecimal amountOpt);

    int reduceAmount(@Param("userNo") String userNo, @Param("amountOpt") BigDecimal amountOpt);
}

