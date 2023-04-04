package com.sara.account.utils;

import com.sara.account.dto.ShopAccountDto;
import com.sara.account.dto.ShopUserDto;
import com.sara.account.entity.ShopAccountEntity;
import com.sara.account.entity.ShopUserEntity;

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

    /**
     * 用户对象转换
     *
     * @param shopUserEntity
     * @return : com.sara.account.dto.ShopUserDto
     * @author: hujunsong
     * @date: 2023/4/1 20:13
     */
    public static ShopUserDto shopUserEntity2Dto(ShopUserEntity shopUserEntity) {
        if (shopUserEntity == null) {
            return null;
        }
        ShopUserDto shopUserDto = new ShopUserDto();
        shopUserDto.setUserNo(shopUserEntity.getUserNo());
        shopUserDto.setUserName(shopUserEntity.getUserName());
        shopUserDto.setActualName(shopUserEntity.getActualName());
        shopUserDto.setSex(shopUserEntity.getSex());
        shopUserDto.setIdNo(shopUserEntity.getIdNo());
        shopUserDto.setPhone(shopUserEntity.getPhone());
        shopUserDto.setEmail(shopUserEntity.getEmail());
        shopUserDto.setLoginPassword(shopUserEntity.getLoginPassword());
        shopUserDto.setPayPassword(shopUserEntity.getPayPassword());
        shopUserDto.setDateCreate(shopUserEntity.getDateCreate());
        shopUserDto.setDateUpdate(shopUserEntity.getDateUpdate());
        shopUserDto.setCreator(shopUserEntity.getCreator());
        shopUserDto.setUpdater(shopUserEntity.getUpdater());

        return shopUserDto;
    }
}
