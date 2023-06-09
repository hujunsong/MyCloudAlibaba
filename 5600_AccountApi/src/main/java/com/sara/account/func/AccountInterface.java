package com.sara.account.func;

import com.sara.account.dto.ShopAccountDto;
import com.sara.utils.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author: hujunsong
 * @date: 2023/4/2 12:10
 */
@FeignClient(name = "AccountCenter")
public interface AccountInterface {

    /**
     * <pre>
     * @description: 创建账号
     * @param userNo  用户号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:29
     * </pre>
     */
    @PostMapping("/account/create")
    CommonResult<ShopAccountDto> createAccount(@RequestParam("userNo") @NotBlank String userNo);

    /**
     * <pre>
     * @description: 用户充值
     * @param userNo 用户号
     * @param amount 充值金额
     * @param orderNo 充值订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:31
     * </pre>
     */
    @PostMapping("/account/add")
    CommonResult<ShopAccountDto> addAmount(@RequestParam("userNo") @NotBlank String userNo, @RequestParam("amount") @Digits(integer = 6, fraction = 2) BigDecimal amount, @RequestParam("orderNo") @NotBlank String orderNo);

    /**
     * <pre>
     * @description: 下单冻结金额
     * @param userNo 用户号
     * @param amount 冻结金额
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:31
     * </pre>
     */
    @PostMapping("/account/lock")
    CommonResult<ShopAccountDto> lockAmount(@RequestParam("userNo") @NotBlank String userNo, @RequestParam("amount") @Digits(integer = 6, fraction = 2) BigDecimal amount, @RequestParam("orderNo") @NotBlank String orderNo);

    /**
     * <pre>
     * @description: 取消订单解冻金额
     * @param userNo 用户号
     * @param amount 解冻金额
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:32
     * </pre>
     */
    @PostMapping("/account/unlock")
    CommonResult<ShopAccountDto> unlockAmount(@RequestParam("userNo") @NotBlank String userNo, @RequestParam("amount") @Digits(integer = 6, fraction = 2) BigDecimal amount, @RequestParam("orderNo") @NotBlank String orderNo);

    /**
     * <pre>
     * @description: 支付订单减少金额
     * @param userNo 用户号
     * @param amount 减少金额
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopAccountDto>
     * @author: hujunsong
     * @date: 2023/3/29 16:33
     * </pre>
     */
    @PostMapping("/account/reduce")
    CommonResult<ShopAccountDto> reduceAmount(@RequestParam("userNo") @NotBlank String userNo, @RequestParam("amount") @Digits(integer = 6, fraction = 2) BigDecimal amount, @RequestParam("orderNo") @NotBlank String orderNo);
}