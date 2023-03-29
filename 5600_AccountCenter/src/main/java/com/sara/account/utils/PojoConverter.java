package com.sara.account.utils;

import com.sara.account.dto.ShopAccountDto;
import com.sara.account.entity.ShopAccountEntity;

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
     * @Param [shopAccountEntity]
     * @Return com.sara.Account.dto.ShopAccountDto
     * @Exception
     */
    public static ShopAccountDto ShopAccountEntity2Dto(ShopAccountEntity shopAccountEntity) {
        if (shopAccountEntity == null) {
            return null;
        }
        ShopAccountDto shopAccountDto = new ShopAccountDto();
        shopAccountDto.setAccountNo(shopAccountEntity.getAccountNo());
        shopAccountDto.setUserNo(shopAccountEntity.getUserNo());
        shopAccountDto.setAmountRemain(shopAccountEntity.getAmountRemain());
        shopAccountDto.setAmountAvailable(shopAccountEntity.getAmountAvailable());
        shopAccountDto.setAmountLock(shopAccountEntity.getAmountLock());
        shopAccountDto.setFlag(shopAccountEntity.getFlag());
        shopAccountDto.setDateCreate(shopAccountEntity.getDateCreate());
        shopAccountDto.setDateUpdate(shopAccountEntity.getDateUpdate());
        shopAccountDto.setCreator(shopAccountEntity.getCreator());
        shopAccountDto.setUpdater(shopAccountEntity.getUpdater());
        return shopAccountDto;
    }
}
