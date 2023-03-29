package com.sara.account.constant;

/**
 * '操作方式:add-增加余额,reduce-减少余额,lock-冻结余额,unlock-解冻余额',
 */
public enum ShopAccountDetailOptTypeEnum {

    SHOP_ACCOUNT_DETAIL_OPT_TYPE_ENUM_ADD("add", "增加余额"),
    SHOP_ACCOUNT_DETAIL_OPT_TYPE_ENUM_REDUCE("reduce", "减少余额"),
    SHOP_ACCOUNT_DETAIL_OPT_TYPE_ENUM_LOCK("lock", "冻结余额"),
    SHOP_ACCOUNT_DETAIL_OPT_TYPE_ENUM_UNLOCK("unlock", "解冻余额");

    private String optType;
    private String optDesc;

    ShopAccountDetailOptTypeEnum(String optType, String optDesc) {
        this.optType = optType;
        this.optDesc = optDesc;
    }

    public String getOptType() {
        return optType;
    }

    public String getOptDesc() {
        return optDesc;
    }
}
