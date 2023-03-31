package com.sara.order.dto;


import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 下订单
 * @author: hujunsong
 * @date: 2023/3/29 18:11
 */
public class ShopOrderApplyDto implements Serializable {
    private static final long serialVersionUID = -31827892178274258L;

    /**
     * 用户号
     */
    @NotBlank(message = "用户号不能为空")
    private String userNo;

    /**
     * 应付金额
     */
    @Digits(message = "应付金额不能为空", integer = 6, fraction = 2)
    private BigDecimal amountPayable;
    /**
     * 优惠金额
     */
    private BigDecimal amountDiscount;

    /**
     * 订单行
     */
    @Valid
    private List<ShopOrderApplyLineDto> shopOrderLineDtoList;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public BigDecimal getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(BigDecimal amountPayable) {
        this.amountPayable = amountPayable;
    }

    public BigDecimal getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(BigDecimal amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public List<ShopOrderApplyLineDto> getShopOrderLineDtoList() {
        return shopOrderLineDtoList;
    }

    public void setShopOrderLineDtoList(List<ShopOrderApplyLineDto> shopOrderLineDtoList) {
        this.shopOrderLineDtoList = shopOrderLineDtoList;
    }
}

