package com.sara.order.service.impl;

import com.sara.order.dao.ShopOrderLineDao;
import com.sara.order.outside.StockInterface;
import com.sara.order.service.ShopOrderService;
import com.sara.order.dao.ShopOrderDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单表(ShopOrder)表服务实现类
 *
 * @author hujunsong
 * @since 2023-03-28 16:54:51
 */
@Service("shopOrderService")
public class ShopOrderServiceImpl implements ShopOrderService {

    @Resource
    private ShopOrderDao shopOrderDao;

    @Resource
    private ShopOrderLineDao shopOrderLineDao;

    /**
     * 加载 openfeign client
     */
    @Resource
    private StockInterface stockInterface;

}
