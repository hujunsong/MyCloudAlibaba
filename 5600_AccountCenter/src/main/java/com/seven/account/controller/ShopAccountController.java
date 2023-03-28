package com.seven.account.controller;

import com.sara.utils.response.CommonResult;
import com.seven.account.entity.ShopAccountEntity;
import com.seven.account.service.ShopAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户接口
 */
@RestController
public class ShopAccountController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ShopAccountService shopAccountService;

    /**
     * 创建账号
     *
     * @param userNo 用户号
     * @return OK
     */
    @ResponseBody
    @PostMapping("/account/create")
    public CommonResult<ShopAccountEntity> createAccount(String userNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.createAccount(userNo);
            return new CommonResult<ShopAccountEntity>().success(shopAccountEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopAccountEntity>().fail(exception.getMessage());
        }
    }

    /**
     * 用户充值
     *
     * @param userNo  用户号
     * @param amount  充值金额
     * @param orderNo 充值订单号
     * @return
     */
    @ResponseBody
    @PostMapping("/account/add")
    public CommonResult<ShopAccountEntity> addAmount(String userNo, BigDecimal amount, String orderNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.addAmount(userNo, amount, orderNo);
            return new CommonResult<ShopAccountEntity>().success(shopAccountEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopAccountEntity>().fail(exception.getMessage());
        }
    }

    /**
     * 下单冻结金额
     *
     * @param userNo  用户号
     * @param amount  冻结金额
     * @param orderNo 订单号
     * @return
     */
    @ResponseBody
    @PostMapping("/account/lock")
    public CommonResult<ShopAccountEntity> lockAmount(String userNo, BigDecimal amount, String orderNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.lockAmount(userNo, amount, orderNo);
            return new CommonResult<ShopAccountEntity>().success(shopAccountEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopAccountEntity>().fail();
        }
    }

    /**
     * 取消订单解冻金额
     *
     * @param userNo  用户号
     * @param amount  解冻金额
     * @param orderNo 订单号
     * @return
     */
    @ResponseBody
    @PostMapping("/account/unlock")
    public CommonResult<ShopAccountEntity> unlockAmount(String userNo, BigDecimal amount, String orderNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.unlockAmount(userNo, amount, orderNo);
            return new CommonResult<ShopAccountEntity>().success(shopAccountEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopAccountEntity>().fail(exception.getMessage());
        }
    }

    /**
     * 支付订单减少金额
     *
     * @param userNo  用户号
     * @param amount  减少金额
     * @param orderNo 订单号
     * @return
     */
    @ResponseBody
    @PostMapping("/account/reduce")
    public CommonResult<ShopAccountEntity> reduceAmount(String userNo, BigDecimal amount, String orderNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.reduceAmount(userNo, amount, orderNo);
            return new CommonResult<ShopAccountEntity>().success(shopAccountEntity);
        } catch (Exception exception) {
            return new CommonResult<ShopAccountEntity>().fail(exception.getMessage());
        }
    }
}