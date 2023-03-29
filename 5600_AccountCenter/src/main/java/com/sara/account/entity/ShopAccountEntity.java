package com.sara.account.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 账户表(ShopAccount)实体类
 *
 * @author makejava
 * @since 2023-03-26 08:22:43
 */
public class ShopAccountEntity implements Serializable {
    private static final long serialVersionUID = 552704620296493245L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 账户号
     */
    private String accountNo;
    /**
     * 用户号
     */
    private String userNo;
    /**
     * 账户余额
     */
    private Double amountRemain;
    /**
     * 可用余额
     */
    private Double amountAvailable;
    /**
     * 冻结余额
     */
    private Double amountLock;
    /**
     * 有效标识:0-无效,1-有效
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

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Double getAmountRemain() {
        return amountRemain;
    }

    public void setAmountRemain(Double amountRemain) {
        this.amountRemain = amountRemain;
    }

    public Double getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Double amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Double getAmountLock() {
        return amountLock;
    }

    public void setAmountLock(Double amountLock) {
        this.amountLock = amountLock;
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

