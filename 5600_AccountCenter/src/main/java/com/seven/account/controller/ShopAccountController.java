package com.seven.account.controller;

import com.seven.account.entity.ShopAccountEntity;
import com.seven.account.service.ShopAccountService;
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
    public ShopAccountEntity createAccount(String userNo) {
        return shopAccountService.createAccount(userNo);
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
    public ShopAccountEntity addAmount(String userNo, BigDecimal amount, String orderNo) {
        return shopAccountService.addAmount(userNo, amount, orderNo);
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
    public ShopAccountEntity lockAmount(String userNo, BigDecimal amount, String orderNo) {
        return shopAccountService.lockAmount(userNo, amount, orderNo);
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
    public ShopAccountEntity unlockAmount(String userNo, BigDecimal amount, String orderNo) {
        return shopAccountService.unlockAmount(userNo, amount, orderNo);
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
    public ShopAccountEntity reduceAmount(String userNo, BigDecimal amount, String orderNo) {
        return shopAccountService.reduceAmount(userNo, amount, orderNo);
    }
}