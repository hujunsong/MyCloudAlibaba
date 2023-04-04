package com.sara.account.dao;

import com.sara.account.entity.ShopUserEntity;

/**
 * 用户表(ShopUser)表数据库访问层
 *
 * @author hujunsong
 * @since 2023-04-01 19:33:22
 */
public interface ShopUserDao {

    /**
     * 通过用户号查询
     *
     * @param userNo 主键
     * @return 实例对象
     */
    ShopUserEntity queryByUserNo(String userNo);

    /**
     * 通过用户名查询
     *
     * @param userName 用户名
     * @return 实例对象
     */
    ShopUserEntity queryByUserName(String userName);

    /**
     * 通过邮箱查询
     *
     * @param email 邮箱
     * @return 实例对象
     */
    ShopUserEntity queryByEmail(String email);

    /**
     * 通过ID查询单条数据
     *
     * @param idNo 身份证
     * @return 实例对象
     */
    ShopUserEntity queryByIdNo(String idNo);

    /**
     * 通过ID查询单条数据
     *
     * @param phone 手机号
     * @return 实例对象
     */
    ShopUserEntity queryByPhone(String phone);

    /**
     * 新增数据
     *
     * @param shopUser 实例对象
     * @return 影响行数
     */
    int insert(ShopUserEntity shopUser);
}

