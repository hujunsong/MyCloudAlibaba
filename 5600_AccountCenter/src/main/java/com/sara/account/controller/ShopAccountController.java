package com.sara.account.controller;

import com.sara.account.dto.ShopAccountDto;
import com.sara.account.entity.ShopAccountEntity;
import com.sara.account.func.AccountInterface;
import com.sara.account.service.ShopAccountService;
import com.sara.account.utils.PojoConverter;
import com.sara.utils.response.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 账户接口
 */
@RestController
public class ShopAccountController implements AccountInterface {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ShopAccountService shopAccountService;

    /**
     * 创建账号
     *
     * @param userNo 用户号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:47
     */
    @Override
    @ResponseBody
    @PostMapping("/account/create")
    public CommonResult<ShopAccountDto> createAccount(@RequestParam("userNo") @NotBlank String userNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.createAccount(userNo);
            return new CommonResult<ShopAccountDto>().success(PojoConverter.ShopAccountEntity2Dto(shopAccountEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopAccountDto>().fail(exception.getMessage());
        }
    }

    /**
     * 用户充值
     *
     * @param userNo  用户号
     * @param amount  充值金额
     * @param orderNo 充值订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:49
     */
    @Override
    @ResponseBody
    @PostMapping("/account/add")
    public CommonResult<ShopAccountDto> addAmount(@RequestParam("userNo") @NotBlank String userNo,
                                                  @RequestParam("amount") @Digits(integer = 6, fraction = 2) BigDecimal amount,
                                                  @RequestParam("orderNo") @NotBlank String orderNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.addAmount(userNo, amount, orderNo);
            return new CommonResult<ShopAccountDto>().success(PojoConverter.ShopAccountEntity2Dto(shopAccountEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopAccountDto>().fail(exception.getMessage());
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
    @Override
    @ResponseBody
    @PostMapping("/account/lock")
    public CommonResult<ShopAccountDto> lockAmount(@RequestParam("userNo") @NotBlank String userNo,
                                                   @RequestParam("amount") @Digits(integer = 6, fraction = 2) BigDecimal amount,
                                                   @RequestParam("orderNo") @NotBlank String orderNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.lockAmount(userNo, amount, orderNo);
            return new CommonResult<ShopAccountDto>().success(PojoConverter.ShopAccountEntity2Dto(shopAccountEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopAccountDto>().fail();
        }
    }

    /**
     * 取消订单解冻金额
     *
     * @param userNo  用户号
     * @param amount  解冻金额
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:50
     */
    @Override
    @ResponseBody
    @PostMapping("/account/unlock")
    public CommonResult<ShopAccountDto> unlockAmount(@RequestParam("userNo") @NotBlank String userNo,
                                                     @RequestParam("amount") @Digits(integer = 6, fraction = 2) BigDecimal amount,
                                                     @RequestParam("orderNo") @NotBlank String orderNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.unlockAmount(userNo, amount, orderNo);
            return new CommonResult<ShopAccountDto>().success(PojoConverter.ShopAccountEntity2Dto(shopAccountEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopAccountDto>().fail(exception.getMessage());
        }
    }

    /**
     * 支付订单减少金额
     *
     * @param userNo  用户号
     * @param amount  减少金额
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:50
     */
    @Override
    @ResponseBody
    @PostMapping("/account/reduce")
    public CommonResult<ShopAccountDto> reduceAmount(@RequestParam("userNo") @NotBlank String userNo,
                                                     @RequestParam("amount") @Digits(integer = 6, fraction = 2) BigDecimal amount,
                                                     @RequestParam("orderNo") @NotBlank String orderNo) {
        try {
            ShopAccountEntity shopAccountEntity = shopAccountService.reduceAmount(userNo, amount, orderNo);
            return new CommonResult<ShopAccountDto>().success(PojoConverter.ShopAccountEntity2Dto(shopAccountEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopAccountDto>().fail(exception.getMessage());
        }
    }
}