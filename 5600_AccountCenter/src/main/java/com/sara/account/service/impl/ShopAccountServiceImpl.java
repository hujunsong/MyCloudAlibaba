package com.sara.account.service.impl;

import com.sara.utils.snowflake.nacos.SnowflakeIdGenerator;
import com.sara.account.dao.ShopAccountDao;
import com.sara.account.dao.ShopAccountDetailDao;
import com.sara.account.entity.ShopAccountDetailEntity;
import com.sara.account.entity.ShopAccountEntity;
import com.sara.account.service.ShopAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static com.sara.account.constant.ShopAccountDetailOptTypeEnum.*;

/**
 * 账户表(ShopAccount)表服务实现类
 *
 * @author makejava
 * @since 2023-03-26 08:22:48
 */
@Service("shopAccountService")
public class ShopAccountServiceImpl implements ShopAccountService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ShopAccountDao shopAccountDao;

    @Resource
    private ShopAccountDetailDao shopAccountDetailDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopAccountEntity createAccount(String userNo) {
        Long id = SnowflakeIdGenerator.nextId();
        String accountNo = "AC" + id;
        shopAccountDao.createAccount(id, accountNo, userNo);
        return shopAccountDao.queryByUserNo(userNo);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param userNo 主键
     * @return 实例对象
     */
    @Override
    public ShopAccountEntity queryByUserNo(String userNo) {
        return this.shopAccountDao.queryByUserNo(userNo);
    }

    /**
     * 账户充值,写入明细
     *
     * @param userNo
     * @param amountOpt
     * @param orderNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopAccountEntity addAmount(String userNo, BigDecimal amountOpt, String orderNo) {

        ShopAccountEntity shopAccountEntity = shopAccountDao.queryByUserNo(userNo);
        if (shopAccountEntity == null) {
            throw new RuntimeException("充值账户不存在,userNo=" + userNo);
        }
        int accountResult = shopAccountDao.addAmount(userNo, amountOpt);
        if (accountResult == 1) {
            ShopAccountDetailEntity shopAccountDetail = new ShopAccountDetailEntity();
            Long id = SnowflakeIdGenerator.nextId();
            String accountDetailNo = "ACD" + id;
            shopAccountDetail.setId(id);
            shopAccountDetail.setAccountDetailNo(accountDetailNo);
            shopAccountDetail.setAccountNo(shopAccountEntity.getAccountNo());
            shopAccountDetail.setUserNo(userNo);
            shopAccountDetail.setAmountOpt(amountOpt);
            shopAccountDetail.setOptType(SHOP_ACCOUNT_DETAIL_OPT_TYPE_ENUM_ADD.getOptType());
            shopAccountDetail.setOrderNo(orderNo);
            shopAccountDetailDao.insertAccountDetail(shopAccountDetail);
        }
        return shopAccountDao.queryByUserNo(userNo);
    }

    /**
     * 冻结金额,写入明细
     *
     * @param userNo
     * @param amountOpt
     * @param orderNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopAccountEntity lockAmount(String userNo, BigDecimal amountOpt, String orderNo) {

        ShopAccountEntity shopAccountEntity = shopAccountDao.queryByUserNo(userNo);
        if (shopAccountEntity == null) {
            throw new RuntimeException("冻结金额,账户不存在,userNo=" + userNo);
        }
        int accountResult = shopAccountDao.lockAmount(userNo, amountOpt);
        if (accountResult == 1) {
            ShopAccountDetailEntity shopAccountDetail = new ShopAccountDetailEntity();
            Long id = SnowflakeIdGenerator.nextId();
            String accountDetailNo = "ACD" + id;
            shopAccountDetail.setId(id);
            shopAccountDetail.setAccountDetailNo(accountDetailNo);
            shopAccountDetail.setAccountNo(shopAccountEntity.getAccountNo());
            shopAccountDetail.setUserNo(userNo);
            shopAccountDetail.setAmountOpt(amountOpt);
            shopAccountDetail.setOptType(SHOP_ACCOUNT_DETAIL_OPT_TYPE_ENUM_LOCK.getOptType());
            shopAccountDetail.setOrderNo(orderNo);
            shopAccountDetailDao.insertAccountDetail(shopAccountDetail);
        }
        return shopAccountDao.queryByUserNo(userNo);
    }

    /**
     * 解冻金额,写入明细
     *
     * @param userNo
     * @param amountOpt
     * @param orderNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopAccountEntity unlockAmount(String userNo, BigDecimal amountOpt, String orderNo) {

        ShopAccountEntity shopAccountEntity = shopAccountDao.queryByUserNo(userNo);
        if (shopAccountEntity == null) {
            throw new RuntimeException("解冻金额,账户不存在,userNo=" + userNo);
        }
        int accountResult = shopAccountDao.unlockAmount(userNo, amountOpt);
        if (accountResult == 1) {
            ShopAccountDetailEntity shopAccountDetail = new ShopAccountDetailEntity();
            Long id = SnowflakeIdGenerator.nextId();
            String accountDetailNo = "ACD" + id;
            shopAccountDetail.setId(id);
            shopAccountDetail.setAccountDetailNo(accountDetailNo);
            shopAccountDetail.setAccountNo(shopAccountEntity.getAccountNo());
            shopAccountDetail.setUserNo(userNo);
            shopAccountDetail.setAmountOpt(amountOpt);
            shopAccountDetail.setOptType(SHOP_ACCOUNT_DETAIL_OPT_TYPE_ENUM_UNLOCK.getOptType());
            shopAccountDetail.setOrderNo(orderNo);
            shopAccountDetailDao.insertAccountDetail(shopAccountDetail);
        }
        return shopAccountDao.queryByUserNo(userNo);
    }

    /**
     * 扣减金额,写入明细
     *
     * @param userNo
     * @param amountOpt
     * @param orderNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopAccountEntity reduceAmount(String userNo, BigDecimal amountOpt, String orderNo) {

        ShopAccountEntity shopAccountEntity = shopAccountDao.queryByUserNo(userNo);
        if (shopAccountEntity == null) {
            throw new RuntimeException("扣减金额,账户不存在,userNo=" + userNo);
        }
        int accountResult = shopAccountDao.reduceAmount(userNo, amountOpt);
        if (accountResult == 1) {
            ShopAccountDetailEntity shopAccountDetail = new ShopAccountDetailEntity();
            Long id = SnowflakeIdGenerator.nextId();
            String accountDetailNo = "ACD" + id;
            shopAccountDetail.setId(id);
            shopAccountDetail.setAccountDetailNo(accountDetailNo);
            shopAccountDetail.setAccountNo(shopAccountEntity.getAccountNo());
            shopAccountDetail.setUserNo(userNo);
            shopAccountDetail.setAmountOpt(amountOpt);
            shopAccountDetail.setOptType(SHOP_ACCOUNT_DETAIL_OPT_TYPE_ENUM_REDUCE.getOptType());
            shopAccountDetail.setOrderNo(orderNo);
            shopAccountDetailDao.insertAccountDetail(shopAccountDetail);
        }
        return shopAccountDao.queryByUserNo(userNo);
    }
}
