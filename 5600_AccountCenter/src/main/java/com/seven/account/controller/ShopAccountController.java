package com.seven.account.controller;

import com.seven.account.entity.ShopAccountEntity;
import com.seven.account.service.ShopAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户表(ShopAccount)表控制层
 *
 * @author makejava
 * @since 2023-03-26 08:22:28
 */
@RestController
public class ShopAccountController {

    @Resource
    private ShopAccountService shopAccountService;

    /**
     * 创建账号
     *
     * @param userNo  用户号
     * @return OK
     */
    @ResponseBody
    @PostMapping("/account/create")
    public String createAccount(String userNo) {
        ShopAccountEntity shopAccountEntity =  shopAccountService.createAccount(userNo);
        if(shopAccountEntity == null || shopAccountEntity.getFlag() == 0){
            return "Fail";
        }
        return "OK";
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
    public String addAmount(String userNo, BigDecimal amount, String orderNo) {
        int result =  shopAccountService.addAmount(userNo,amount,orderNo);
        if(result == 1){
            return "OK";
        }
        return "FAIL";
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
    public String lockAmount(String userNo, BigDecimal amount, String orderNo) {
        int result =  shopAccountService.lockAmount(userNo,amount,orderNo);
        if(result == 1){
            return "OK";
        }
        return "FAIL";
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
    public String unlockAmount(String userNo, BigDecimal amount, String orderNo) {
        int result =  shopAccountService.unlockAmount(userNo,amount,orderNo);
        if(result == 1){
            return "OK";
        }
        return "FAIL";
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
    public String reduceAmount(String userNo, BigDecimal amount, String orderNo) {
        int result =  shopAccountService.reduceAmount(userNo,amount,orderNo);
        if(result == 1){
            return "OK";
        }
        return "FAIL";
    }
}

