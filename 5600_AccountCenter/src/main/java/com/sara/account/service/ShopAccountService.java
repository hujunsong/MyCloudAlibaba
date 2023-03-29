package com.sara.account.service;

import com.sara.account.entity.ShopAccountEntity;

import java.math.BigDecimal;

/**
 * 账户表(ShopAccount)表服务接口
 *
 * @author makejava
 * @since 2023-03-26 08:22:48
 */
public interface ShopAccountService {

    ShopAccountEntity createAccount(String userNo);

    /**
     * 通过userNo查询单条数据
     *
     * @param userNo 主键
     * @return 实例对象
     */
    ShopAccountEntity queryByUserNo(String userNo);

    ShopAccountEntity addAmount(String userNo, BigDecimal amountOpt, String orderNo);

    ShopAccountEntity lockAmount(String userNo, BigDecimal amountOpt, String orderNo);

    ShopAccountEntity unlockAmount(String userNo, BigDecimal amountOpt, String orderNo);

    ShopAccountEntity reduceAmount(String userNo, BigDecimal amountOpt, String orderNo);
}
