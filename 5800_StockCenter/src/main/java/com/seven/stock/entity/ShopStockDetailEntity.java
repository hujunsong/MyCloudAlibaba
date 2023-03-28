package com.seven.stock.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存明细表(ShopStockDetail)实体类
 *
 * @author hujunsong
 * @since 2023-03-27 14:15:31
 */
public class ShopStockDetailEntity implements Serializable {
    private static final long serialVersionUID = 638511494362960562L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 库存明细编号
     */
    private String stockDetailNo;
    /**
     * 库存编号
     */
    private String stockNo;
    /**
     * 物料代码
     */
    private String skuNo;
    /**
     * 操作数量
     */
    private Integer numOpt;
    /**
     * 操作类型:add-增加,reduce-减少,lock-冻结
     */
    private String optType;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 创建时间
     */
    private Date dateCreate;
    /**
     * 更新时间
     */
    private Date dateUpdate;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 更新人
     */
    private String updater;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockDetailNo() {
        return stockDetailNo;
    }

    public void setStockDetailNo(String stockDetailNo) {
        this.stockDetailNo = stockDetailNo;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public Integer getNumOpt() {
        return numOpt;
    }

    public void setNumOpt(Integer numOpt) {
        this.numOpt = numOpt;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}

