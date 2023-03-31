package com.sara.order.utils;

import com.sara.order.dto.ShopOrderDto;
import com.sara.order.entity.ShopOrderEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对象转换
 * @author: hujunsong
 * @date: 2023/3/30 20:30
 */
public class PojoConvertor {

    public static ShopOrderDto shopOrderEntity2Dto(ShopOrderEntity shopOrderEntity) {
        if (shopOrderEntity == null) {
            return null;
        }
        ShopOrderDto shopOrderDto = new ShopOrderDto();
        shopOrderDto.setOrderNo(shopOrderEntity.getOrderNo());
        shopOrderDto.setUserNo(shopOrderEntity.getUserNo());
        shopOrderDto.setOrderStatus(shopOrderEntity.getOrderStatus());
        shopOrderDto.setAmountPayable(shopOrderEntity.getAmountPayable());
        shopOrderDto.setAmountActual(shopOrderEntity.getAmountActual());
        shopOrderDto.setAmountDiscount(shopOrderEntity.getAmountDiscount());
        shopOrderDto.setDatePaid(shopOrderEntity.getDatePaid());
        shopOrderDto.setDateShipped(shopOrderEntity.getDateShipped());
        shopOrderDto.setCourierCompany(shopOrderEntity.getCourierCompany());
        shopOrderDto.setCourierNo(shopOrderEntity.getCourierNo());
        shopOrderDto.setDateReceived(shopOrderEntity.getDateReceived());
        shopOrderDto.setCancelDate(shopOrderEntity.getCancelDate());
        shopOrderDto.setCanceler(shopOrderEntity.getCanceler());
        shopOrderDto.setCancelReason(shopOrderEntity.getCancelReason());
        shopOrderDto.setLockDate(shopOrderEntity.getLockDate());
        shopOrderDto.setLocker(shopOrderEntity.getLocker());
        shopOrderDto.setLockReason(shopOrderEntity.getLockReason());
        shopOrderDto.setCreateDate(shopOrderEntity.getCreateDate());
        shopOrderDto.setUpdateDate(shopOrderEntity.getUpdateDate());
        shopOrderDto.setCreator(shopOrderEntity.getCreator());
        shopOrderDto.setUpdater(shopOrderEntity.getUpdater());
        return shopOrderDto;
    }

    public static List<ShopOrderDto> shopOrderEntityList2Dto(List<ShopOrderEntity> shopOrderEntityList) {
        if (null == shopOrderEntityList) {
            return null;
        }
        List<ShopOrderDto> shopOrderDtoList = new ArrayList<>();
        for (int i = 0; i < shopOrderEntityList.size(); i++) {
            shopOrderDtoList.add(shopOrderEntity2Dto(shopOrderEntityList.get(i)));
        }
        return shopOrderDtoList;
    }
}
