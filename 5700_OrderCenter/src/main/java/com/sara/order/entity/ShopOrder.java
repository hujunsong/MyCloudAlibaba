package com.sara.order.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 订单表(ShopOrder)实体类
 *
 * @author hujunsong
 * @since 2023-03-28 16:54:49
 */
public class ShopOrder implements Serializable {
    private static final long serialVersionUID = -31827892178274258L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 用户号
     */
    private String userNo;
    /**
     * 订单状态:apply_unpaid-已下单未支付,paid_unshipped-已支付未发货,locked-已锁定不发货,canceled-已取消未退款,refunded-已退款,shipped_unreceived-已发货待签收,received-已签收,rejected-已拒收
     */
    private String orderStatus;
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
     * 支付时间
     */
    private Date datePaid;
    /**
     * 发货时间
     */
    private Date dateShipped;
    /**
     * 快递公司:SF-顺丰,YTO-圆通,EMS-邮政
     */
    private String courierCompany;
    /**
     * 快递单号
     */
    private String courierNo;
    /**
     * 签收时间
     */
    private Date dateReceived;
    /**
     * 取消时间
     */
    private Date cancelDate;
    /**
     * 取消人
     */
    private String canceler;
    /**
     * 取消原因
     */
    private String cancelReason;
    /**
     * 锁定时间
     */
    private Date lockDate;
    /**
     * 锁定人
     */
    private String locker;
    /**
     * 锁定原因
     */
    private String lockReason;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany;
    }

    public String getCourierNo() {
        return courierNo;
    }

    public void setCourierNo(String courierNo) {
        this.courierNo = courierNo;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getCanceler() {
        return canceler;
    }

    public void setCanceler(String canceler) {
        this.canceler = canceler;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public String getLocker() {
        return locker;
    }

    public void setLocker(String locker) {
        this.locker = locker;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
