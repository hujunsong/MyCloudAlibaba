package com.seven.stock.constant;

/**
 * 操作类型:add-增加,reduce-减少,lock-冻结
 */
public enum ShopStockOptTypeEnum {

    STOCK_OPT_TYPE_ADD("add", "增加"),
    STOCK_OPT_TYPE_REDUCE("reduce", "减少"),
    STOCK_OPT_TYPE_LOCK("lock", "冻结"),
    STOCK_OPT_TYPE_UNLOCK("unlock", "解冻");

    private String optType;
    private String optDesc;

    ShopStockOptTypeEnum(String optType, String optDesc) {
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
