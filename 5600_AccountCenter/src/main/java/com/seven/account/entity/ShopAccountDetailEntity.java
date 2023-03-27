package com.seven.account.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * 账户明细表(ShopAccountDetail)实体类
 *
 * @author makejava
 * @since 2023-03-26 10:38:15
 */
public class ShopAccountDetailEntity implements Serializable {
    private static final long serialVersionUID = -58041621315192941L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户号
     */
    private String userNo;
    /**
     * 操作金额
     */
    private BigDecimal amountOpt;
    /**
     * 操作方式:add-增加余额,reduce-减少余额,lock-冻结余额
     */
    private String optType;
    /**
     * 关联订单号
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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public BigDecimal getAmountOpt() {
        return amountOpt;
    }

    public void setAmountOpt(BigDecimal amountOpt) {
        this.amountOpt = amountOpt;
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

