package com.seven.stock.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存表(ShopStock)实体类
 *
 * @author hujunsong
 * @since 2023-03-27 11:37:02
 */
public class ShopStockEntity implements Serializable {
    private static final long serialVersionUID = 408544129463966273L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 库存编号
     */
    private String stockNo;
    /**
     * 物料代码
     */
    private String skuNo;
    /**
     * 库存总数量
     */
    private Integer stockTotal;
    /**
     * 锁定数量
     */
    private Integer stockLock;
    /**
     * 可用数量
     */
    private Integer stockAvailable;
    /**
     * 生效标识:0-生效,1-失效
     */
    private Integer flag;
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

    public Integer getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(Integer stockTotal) {
        this.stockTotal = stockTotal;
    }

    public Integer getStockLock() {
        return stockLock;
    }

    public void setStockLock(Integer stockLock) {
        this.stockLock = stockLock;
    }

    public Integer getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(Integer stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

