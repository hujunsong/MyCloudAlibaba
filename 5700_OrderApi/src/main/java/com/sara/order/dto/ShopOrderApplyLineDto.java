package com.sara.order.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 下单物料
 * @author: hujunsong
 * @date: 2023/3/29 18:10
 */
public class ShopOrderApplyLineDto implements Serializable {
    private static final long serialVersionUID = -70451960698142234L;
    /**
     * 物料代码
     */
    @NotBlank(message = "物料代码不能为空")
    private String skuNo;
    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String skuName;
    /**
     * 数量
     */
    @Min(value = 1, message = "下单物料不能小于0")
    private Integer quantity;
    /**
     * 单价
     */
    @Digits(integer = 6, fraction = 2, message = "单价不能为空")
    private BigDecimal price;
    /**
     * 应付金额
     */
    @Digits(integer = 6, fraction = 2, message = "应付金额不能为空")
    private BigDecimal amountPayable;
    /**
     * 优惠金额
     */
    private BigDecimal amountDiscount;

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}

