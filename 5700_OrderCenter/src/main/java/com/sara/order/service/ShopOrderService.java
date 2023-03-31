package com.sara.order.service;

import com.sara.order.dto.ShopOrderApplyDto;
import com.sara.order.entity.ShopOrderEntity;

import java.util.List;

/**
 * @description: 订单服务
 * @author: hujunsong
 * @date: 2023/3/30 20:26
 */
public interface ShopOrderService {

    /**
     * 下单
     *
     * @param shopOrderApplyDto
     * @return : com.sara.order.entity.ShopOrderEntity
     * @author: hujunsong
     * @date: 2023/3/30 20:26
     */
    ShopOrderEntity applyOrder(ShopOrderApplyDto shopOrderApplyDto);

    /**
     * 根据订单号查询
     *
     * @param orderNo
     * @return : com.sara.order.entity.ShopOrderEntity
     * @author: hujunsong
     * @date: 2023/3/30 20:27
     */
    ShopOrderEntity queryByOrderNo(String orderNo);

    /**
     * 根据用户号查询
     *
     * @param userNo
     * @return : java.util.List<com.sara.order.entity.ShopOrderEntity>
     * @author: hujunsong
     * @date: 2023/3/30 20:29
     */
    List<ShopOrderEntity> queryByUserNo(String userNo);
}
