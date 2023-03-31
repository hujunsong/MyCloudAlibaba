package com.sara.stock.service.impl;

import com.sara.stock.dao.ShopStockDao;
import com.sara.stock.dao.ShopStockDetailDao;
import com.sara.stock.dto.ShopStockOptDetailDto;
import com.sara.stock.entity.ShopStockDetailEntity;
import com.sara.stock.entity.ShopStockEntity;
import com.sara.stock.service.ShopStockService;
import com.sara.utils.snowflake.nacos.SnowflakeIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.sara.stock.constant.ShopStockOptTypeEnum.*;

/**
 * 库存表(ShopStock)表服务实现类
 *
 * @author hujunsong
 * @since 2023-03-27 11:37:05
 */
@Service("shopStockService")
public class ShopStockServiceImpl implements ShopStockService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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
        long id = SnowflakeIdGenerator.nextId();
        String stockNo = "ST" + id;
        shopStockDao.createStock(id, stockNo, skuNo);
        return shopStockDao.queryBySkuNo(skuNo);
    }

    /**
     * 批量添加库存
     *
     * @param shopStockLockDtoList
     * @param flowNo
     * @return : com.sara.stock.entity.ShopStockEntity
     * @author: hujunsong
     * @date: 2023/3/30 23:17
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void addStockBatch(List<ShopStockOptDetailDto> shopStockLockDtoList, String flowNo) {
        for (int i = 0; i < shopStockLockDtoList.size(); i++) {
            this.addStock(shopStockLockDtoList.get(i).getSkuNo(), shopStockLockDtoList.get(i).getNums(), flowNo);
        }
    }

    /**
     * 添加库存
     *
     * @param skuNo
     * @param numsOpt
     * @param flowNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopStockEntity addStock(String skuNo, Integer numsOpt, String flowNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        String stockNo = shopStockEntity == null ? null : shopStockEntity.getStockNo();
        if (shopStockEntity == null) {
            long stockId = SnowflakeIdGenerator.nextId();
            stockNo = "ST" + stockId;
            shopStockDao.createStock(stockId, stockNo, skuNo);
        }
        int result = shopStockDao.addStock(skuNo, numsOpt);
        if (result == 1) {
            ShopStockDetailEntity shopStockDetailEntity = new ShopStockDetailEntity();
            long id = SnowflakeIdGenerator.nextId();
            String stockDetailNo = "STD" + id;
            shopStockDetailEntity.setId(id);
            shopStockDetailEntity.setStockDetailNo(stockDetailNo);
            shopStockDetailEntity.setStockNo(stockNo);
            shopStockDetailEntity.setSkuNo(skuNo);
            shopStockDetailEntity.setNumOpt(numsOpt);
            shopStockDetailEntity.setOptType(STOCK_OPT_TYPE_ADD.getOptType());
            shopStockDetailEntity.setFlowNo(flowNo);
            shopStockDetailDao.addStockDetail(shopStockDetailEntity);
            return shopStockDao.queryBySkuNo(skuNo);
        }
        throw new RuntimeException("添加库存,失败,flowNo=" + flowNo);
    }

    /**
     * 批量冻结库存
     *
     * @param shopStockLockDtoList
     * @param flowNo
     * @return : java.util.List<com.sara.stock.entity.ShopStockEntity>
     * @author: hujunsong
     * @date: 2023/3/30 23:14
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void batchLockStock(List<ShopStockOptDetailDto> shopStockLockDtoList, String flowNo) {
        for (int i = 0; i < shopStockLockDtoList.size(); i++) {
            this.lockStock(shopStockLockDtoList.get(i).getSkuNo(), shopStockLockDtoList.get(i).getNums(), flowNo);
        }
    }

    /**
     * 冻结库存
     *
     * @param skuNo
     * @param numsOpt
     * @param flowNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopStockEntity lockStock(String skuNo, Integer numsOpt, String flowNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        if (shopStockEntity == null) {
            throw new RuntimeException("冻结库存,库存信息不存在,skuNo=" + skuNo);
        }
        int result = shopStockDao.lockStock(skuNo, numsOpt);
        if (result == 1) {
            ShopStockDetailEntity shopStockDetailEntity = new ShopStockDetailEntity();
            long id = SnowflakeIdGenerator.nextId();
            String stockDetailNo = "STD" + id;
            shopStockDetailEntity.setId(id);
            shopStockDetailEntity.setStockDetailNo(stockDetailNo);
            shopStockDetailEntity.setStockNo(shopStockEntity.getStockNo());
            shopStockDetailEntity.setSkuNo(skuNo);
            shopStockDetailEntity.setNumOpt(numsOpt);
            shopStockDetailEntity.setOptType(STOCK_OPT_TYPE_LOCK.getOptType());
            shopStockDetailEntity.setFlowNo(flowNo);
            shopStockDetailDao.addStockDetail(shopStockDetailEntity);
            return shopStockDao.queryBySkuNo(skuNo);
        }
        throw new RuntimeException("冻结库存,失败,flowNo=" + flowNo);
    }

    /**
     * 批量解冻库存
     *
     * @param shopStockLockDtoList
     * @param flowNo
     * @return : void
     * @author: hujunsong
     * @date: 2023/3/31 08:43
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void batchUnlockStock(List<ShopStockOptDetailDto> shopStockLockDtoList, String flowNo) {
        for (int i = 0; i < shopStockLockDtoList.size(); i++) {
            this.unlockStock(shopStockLockDtoList.get(i).getSkuNo(), shopStockLockDtoList.get(i).getNums(), flowNo);
        }
    }

    /**
     * 解冻库存
     *
     * @param skuNo
     * @param numsOpt
     * @param flowNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopStockEntity unlockStock(String skuNo, Integer numsOpt, String flowNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        if (shopStockEntity == null) {
            throw new RuntimeException("解冻库存,库存信息不存在,skuNo=" + skuNo);
        }
        int result = shopStockDao.unlockStock(skuNo, numsOpt);
        if (result == 1) {
            ShopStockDetailEntity shopStockDetailEntity = new ShopStockDetailEntity();
            long id = SnowflakeIdGenerator.nextId();
            String stockDetailNo = "STD" + id;
            shopStockDetailEntity.setId(id);
            shopStockDetailEntity.setStockDetailNo(stockDetailNo);
            shopStockDetailEntity.setStockNo(shopStockEntity.getStockNo());
            shopStockDetailEntity.setSkuNo(skuNo);
            shopStockDetailEntity.setNumOpt(numsOpt);
            shopStockDetailEntity.setOptType(STOCK_OPT_TYPE_UNLOCK.getOptType());
            shopStockDetailEntity.setFlowNo(flowNo);
            shopStockDetailDao.addStockDetail(shopStockDetailEntity);
            return shopStockDao.queryBySkuNo(skuNo);
        }
        throw new RuntimeException("解冻库存,失败,flowNo=" + flowNo);
    }

    /**
     * 批量减少库存
     *
     * @param shopStockLockDtoList
     * @param flowNo
     * @return : void
     * @author: hujunsong
     * @date: 2023/3/31 08:43
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void batchReduceStock(List<ShopStockOptDetailDto> shopStockLockDtoList, String flowNo) {
        for (int i = 0; i < shopStockLockDtoList.size(); i++) {
            this.reduceStock(shopStockLockDtoList.get(i).getSkuNo(), shopStockLockDtoList.get(i).getNums(), flowNo);
        }
    }

    /**
     * 减少库存
     *
     * @param skuNo
     * @param numsOpt
     * @param flowNo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public ShopStockEntity reduceStock(String skuNo, Integer numsOpt, String flowNo) {
        ShopStockEntity shopStockEntity = shopStockDao.queryBySkuNo(skuNo);
        if (shopStockEntity == null) {
            throw new RuntimeException("减少库存,库存信息不存在,skuNo=" + skuNo);
        }
        int result = shopStockDao.reduceStock(skuNo, numsOpt);
        if (result == 1) {
            ShopStockDetailEntity shopStockDetailEntity = new ShopStockDetailEntity();
            long id = SnowflakeIdGenerator.nextId();
            String stockDetailNo = "STD" + id;
            shopStockDetailEntity.setId(id);
            shopStockDetailEntity.setStockDetailNo(stockDetailNo);
            shopStockDetailEntity.setStockNo(shopStockEntity.getStockNo());
            shopStockDetailEntity.setSkuNo(skuNo);
            shopStockDetailEntity.setNumOpt(numsOpt);
            shopStockDetailEntity.setOptType(STOCK_OPT_TYPE_REDUCE.getOptType());
            shopStockDetailEntity.setFlowNo(flowNo);
            shopStockDetailDao.addStockDetail(shopStockDetailEntity);
            return shopStockDao.queryBySkuNo(skuNo);
        }
        throw new RuntimeException("减少库存,失败,flowNo=" + flowNo);
    }
}
