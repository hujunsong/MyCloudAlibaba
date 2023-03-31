package com.sara.order.constant;

/**
 * @description: 订单状态枚举
 * @author: hujunsong
 * @date: 2023/3/30 21:05
 */
public enum OrderStatusEnum {

    apply_unpaid("apply_unpaid", "已下单未支付"),
    paid_unshipped("paid_unshipped", "已支付未发货"),
    locked("locked", "已锁定不发货"),
    canceled("canceled", "已取消未退款"),
    refunded("refunded", "已退款"),
    shipped_unreceived("shipped_unreceived", "已发货待签收"),
    received("received", "已签收"),
    rejected("rejected", "已拒收");

    private String code;
    private String desc;

    OrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
