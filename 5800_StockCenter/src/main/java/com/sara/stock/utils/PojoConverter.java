package com.sara.stock.utils;

import com.sara.stock.dto.ShopStockDto;
import com.sara.stock.entity.ShopStockEntity;

/**
 * @Description: 对象转换
 * @Author: hujunsong
 * @Date: 2023/3/29 11:29
 * @Version V1.0.0
 */
public class PojoConverter {

    /**
     * @Description: 库存对象转换
     * @Author: hujunsong
     * @Date: 2023/3/29 11:38
     * @Param [shopStockEntity]
     * @Return com.sara.stock.dto.ShopStockDto
     * @Exception
     */
    public static ShopStockDto ShopStockEntity2Dto(ShopStockEntity shopStockEntity) {
        if (shopStockEntity == null) {
            return null;
        }
        ShopStockDto shopStockDto = new ShopStockDto();
        shopStockDto.setStockNo(shopStockEntity.getStockNo());
        shopStockDto.setSkuNo(shopStockEntity.getSkuNo());
        shopStockDto.setStockTotal(shopStockEntity.getStockTotal());
        shopStockDto.setStockLock(shopStockEntity.getStockLock());
        shopStockDto.setStockAvailable(shopStockEntity.getStockAvailable());
        shopStockDto.setFlag(shopStockEntity.getFlag());
        shopStockDto.setDateCreate(shopStockEntity.getDateCreate());
        shopStockDto.setDateUpdate(shopStockEntity.getDateUpdate());
        shopStockDto.setCreator(shopStockEntity.getCreator());
        shopStockDto.setUpdater(shopStockEntity.getUpdater());
        return shopStockDto;
    }
}
