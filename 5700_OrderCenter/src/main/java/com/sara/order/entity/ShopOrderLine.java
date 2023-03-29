package com.sara.order.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 订单行(ShopOrderLine)实体类
 *
 * @author hujunsong
 * @since 2023-03-28 16:55:12
 */
public class ShopOrderLine implements Serializable {
    private static final long serialVersionUID = -70451960698142234L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 订单行号
     */
    private String orderLineNo;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 用户号
     */
    private String userNo;
    /**
     * 物料代码
     */
    private String skuNo;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 单价
     */
    private Double price;
    /**
     * 应付金额
     */
    private Double amountPayable;
    /**
     * 实付金额
     */
    private Double amountActual;
    /**
     * 优惠金额
     */
    private Double amountDiscount;
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

    public String getOrderLineNo() {
        return orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
        this.orderLineNo = orderLineNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Double getAmountActual() {
        return amountActual;
    }

    public void setAmountActual(Double amountActual) {
        this.amountActual = amountActual;
    }

    public Double getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(Double amountDiscount) {
        this.amountDiscount = amountDiscount;
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

