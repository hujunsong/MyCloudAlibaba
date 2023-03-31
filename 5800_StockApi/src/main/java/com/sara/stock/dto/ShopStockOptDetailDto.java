package com.sara.stock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * @description: 锁定库存
 * @author: hujunsong
 * @date: 2023/3/30 22:54
 */
public class ShopStockOptDetailDto implements Serializable {

    private static final long serialVersionUID = -4845968761086165843L;

    /**
     * 物料代码
     */
    @NotBlank(message = "物料代码不能为空")
    private String skuNo;

    /**
     * 数量
     */
    @Min(value = 1, message = "数量必须大于0")
    private Integer nums;

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }
}
