package com.seven.stock.service.impl;

import com.sara.utils.snowflake.nacos.SnowflakeIdGenerator;
import com.seven.stock.dao.ShopStockDao;
import com.seven.stock.dao.ShopStockDetailDao;
import com.seven.stock.entity.ShopStockDetailEntity;
import com.seven.stock.entity.ShopStockEntity;
import com.seven.stock.service.ShopStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.seven.stock.constant.ShopStockOptTypeEnum.*;

/**
 * 库存表(ShopStock)表服务实现类
 *
 * @author hujunsong
 * @since 2023-03-27 11:37:05
 */
@Service("shopStockService")
public class ShopStockServiceImpl implements ShopStockService {

    @Resource
    private ShopStockDao shopStockDao;

    @Resource
    private ShopStockDetailDao shopStockDetailDao;

    /**
     * 查询单条数据
     *
     * @param skuNo 主键
     * @return 实例对象
     */
    @Override
    public ShopStockEntity queryBySkuNo(String skuNo) {
        return shopStockDao.queryBySkuNo(skuNo);
    }

    /**
     * 创建库存
     *
     * @param skuNo 物料代码
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopStockEntity createStock(String skuNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        if (shopStockEntity != null && shopStockEntity.getFlag() != 0) {
            return shopStockEntity;
        }
        shopStockDao.createStock(SnowflakeIdGenerator.nextId(), skuNo);
        return shopStockDao.queryBySkuNo(skuNo);
    }

    /**
     * 添加库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopStockEntity addStock(String skuNo, Integer numsOpt, String orderNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        if (shopStockEntity == null) {
            shopStockDao.createStock(SnowflakeIdGenerator.nextId(), skuNo);
        }
        int result = shopStockDao.addStock(skuNo, numsOpt);
        if (result == 1) {
            ShopStockDetailEntity shopStockDetailEntity = new ShopStockDetailEntity();
            shopStockDetailEntity.setId(SnowflakeIdGenerator.nextId());
            shopStockDetailEntity.setSkuNo(skuNo);
            shopStockDetailEntity.setNumOpt(numsOpt);
            shopStockDetailEntity.setOptType(STOCK_OPT_TYPE_ADD.getOptType());
            shopStockDetailEntity.setOrderNo(orderNo);
            shopStockDetailDao.addStockDetail(shopStockDetailEntity);
            return shopStockDao.queryBySkuNo(skuNo);
        }
        throw new RuntimeException("添加库存,失败,orderNo=" + orderNo);
    }

    /**
     * 冻结库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    @Override
    public ShopStockEntity lockStock(String skuNo, Integer numsOpt, String orderNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        if (shopStockEntity == null) {
            throw new RuntimeException("冻结库存,库存信息不存在,skuNo=" + skuNo);
        }
        int result = shopStockDao.lockStock(skuNo, numsOpt);
        if (result == 1) {
            ShopStockDetailEntity shopStockDetailEntity = new ShopStockDetailEntity();
            shopStockDetailEntity.setId(SnowflakeIdGenerator.nextId());
            shopStockDetailEntity.setSkuNo(skuNo);
            shopStockDetailEntity.setNumOpt(numsOpt);
            shopStockDetailEntity.setOptType(STOCK_OPT_TYPE_LOCK.getOptType());
            shopStockDetailEntity.setOrderNo(orderNo);
            shopStockDetailDao.addStockDetail(shopStockDetailEntity);
            return shopStockDao.queryBySkuNo(skuNo);
        }
        throw new RuntimeException("冻结库存,失败,orderNo=" + orderNo);
    }

    /**
     * 解冻库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    @Override
    public ShopStockEntity unlockStock(String skuNo, Integer numsOpt, String orderNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        if (shopStockEntity == null) {
            throw new RuntimeException("解冻库存,库存信息不存在,skuNo=" + skuNo);
        }
        int result = shopStockDao.unlockStock(skuNo, numsOpt);
        if (result == 1) {
            ShopStockDetailEntity shopStockDetailEntity = new ShopStockDetailEntity();
            shopStockDetailEntity.setId(SnowflakeIdGenerator.nextId());
            shopStockDetailEntity.setSkuNo(skuNo);
            shopStockDetailEntity.setNumOpt(numsOpt);
            shopStockDetailEntity.setOptType(STOCK_OPT_TYPE_UNLOCK.getOptType());
            shopStockDetailEntity.setOrderNo(orderNo);
            shopStockDetailDao.addStockDetail(shopStockDetailEntity);
            return shopStockDao.queryBySkuNo(skuNo);
        }
        throw new RuntimeException("解冻库存,失败,orderNo=" + orderNo);
    }

    /**
     * 减少库存
     *
     * @param skuNo
     * @param numsOpt
     * @param orderNo
     * @return
     */
    @Override
    public ShopStockEntity reduceStock(String skuNo, Integer numsOpt, String orderNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        if (shopStockEntity == null) {
            throw new RuntimeException("减少库存,库存信息不存在,skuNo=" + skuNo);
        }
        int result = shopStockDao.reduceStock(skuNo, numsOpt);
        if (result == 1) {
            ShopStockDetailEntity shopStockDetailEntity = new ShopStockDetailEntity();
            shopStockDetailEntity.setId(SnowflakeIdGenerator.nextId());
            shopStockDetailEntity.setSkuNo(skuNo);
            shopStockDetailEntity.setNumOpt(numsOpt);
            shopStockDetailEntity.setOptType(STOCK_OPT_TYPE_REDUCE.getOptType());
            shopStockDetailEntity.setOrderNo(orderNo);
            shopStockDetailDao.addStockDetail(shopStockDetailEntity);
            return shopStockDao.queryBySkuNo(skuNo);
        }
        throw new RuntimeException("减少库存,失败,orderNo=" + orderNo);
    }
}
