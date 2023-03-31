package com.sara.order.global;

import com.sara.order.entity.ShopOrderEntity;
import com.sara.order.entity.ShopOrderLineEntity;
import com.sara.stock.dto.ShopStockOptDto;

import java.util.List;

public interface ApplyOrderGlobalService {

    /**
     * 下单分布式事务
     *
     * @param shopStockOptDto
     * @param shopOrderEntity
     * @param shopOrderLineEntityList
     * @return : void
     * @author: hujunsong
     * @date: 2023/3/31 11:48
     */
    ShopOrderEntity applyOrderGlobal(ShopStockOptDto shopStockOptDto, ShopOrderEntity shopOrderEntity,
                          List<ShopOrderLineEntity> shopOrderLineEntityList);
}