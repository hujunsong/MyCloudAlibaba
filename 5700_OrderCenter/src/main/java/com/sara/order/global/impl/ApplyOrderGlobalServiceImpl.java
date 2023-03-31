package com.sara.order.global.impl;

import com.sara.order.dao.ShopOrderDao;
import com.sara.order.dao.ShopOrderLineDao;
import com.sara.order.entity.ShopOrderEntity;
import com.sara.order.entity.ShopOrderLineEntity;
import com.sara.order.global.ApplyOrderGlobalService;
import com.sara.stock.dto.ShopStockOptDto;
import com.sara.stock.func.StockInterface;
import com.sara.utils.response.CommonResult;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 下单分布式事务
 *
 * @author: hujunsong
 * @date: 2023/3/31 11:42
 */
@Service("applyOrderGlobalService")
public class ApplyOrderGlobalServiceImpl implements ApplyOrderGlobalService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private StockInterface stockInterface;

    @Resource
    private ShopOrderDao shopOrderDao;

    @Resource
    private ShopOrderLineDao shopOrderLineDao;

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
    @GlobalTransactional
    @Override
    public ShopOrderEntity applyOrderGlobal(ShopStockOptDto shopStockOptDto, ShopOrderEntity shopOrderEntity,
                                            List<ShopOrderLineEntity> shopOrderLineEntityList) {

        //锁定库存
        CommonResult<Void> stockResult = stockInterface.lockStock(shopStockOptDto);
        if (!stockResult.isSuccess()) {
            logger.error("下单失败,shopOrderEntity={},stockResult={}", shopOrderEntity, stockResult);
            throw new RuntimeException("下单异常");
        }

        // 写订单数据
        shopOrderDao.insert(shopOrderEntity);
        // 写订单详情
        shopOrderLineDao.insertBatch(shopOrderLineEntityList);
        // 查询返回
        return shopOrderDao.queryByOrderNo(shopOrderEntity.getOrderNo());
    }
}
