package com.sara.stock.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @description: 库存操作
 * @author: hujunsong
 * @date: 2023/3/31 09:35
 */
public class ShopStockOptDto implements Serializable {

    /**
     * 操作流水
     */
    @NotBlank(message = "库存操作流水号不能为空")
    private String flowNo;

    /**
     * 操作明细
     */
    @Valid
    @NotEmpty
    private List<ShopStockOptDetailDto> shopStockOptDetailDtoList;

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public List<ShopStockOptDetailDto> getShopStockOptDetailDtoList() {
        return shopStockOptDetailDtoList;
    }

    public void setShopStockOptDetailDtoList(List<ShopStockOptDetailDto> shopStockOptDetailDtoList) {
        this.shopStockOptDetailDtoList = shopStockOptDetailDtoList;
    }
}
