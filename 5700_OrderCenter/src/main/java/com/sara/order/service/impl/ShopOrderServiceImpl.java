package com.sara.order.service.impl;

import com.sara.order.constant.OrderStatusEnum;
import com.sara.order.dao.ShopOrderDao;
import com.sara.order.dto.ShopOrderApplyDto;
import com.sara.order.dto.ShopOrderApplyLineDto;
import com.sara.order.entity.ShopOrderEntity;
import com.sara.order.entity.ShopOrderLineEntity;
import com.sara.order.global.ApplyOrderGlobalService;
import com.sara.order.service.ShopOrderService;
import com.sara.stock.dto.ShopStockOptDetailDto;
import com.sara.stock.dto.ShopStockOptDto;
import com.sara.utils.snowflake.nacos.SnowflakeIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单表(ShopOrder)表服务实现类
 *
 * @author hujunsong
 * @since 2023-03-28 16:54:51
 */
@Service("shopOrderService")
public class ShopOrderServiceImpl implements ShopOrderService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ShopOrderDao shopOrderDao;

    @Resource
    private ApplyOrderGlobalService applyOrderGlobalService;

    /**
     * 下单
     *
     * @param shopOrderApplyDto
     * @return : com.sara.order.entity.ShopOrderEntity
     * @author: hujunsong
     * @date: 2023/3/30 20:26
     */
    @Override
    public ShopOrderEntity applyOrder(ShopOrderApplyDto shopOrderApplyDto) {

        // 订单
        ShopOrderEntity shopOrderEntity = new ShopOrderEntity();
        List<ShopOrderLineEntity> shopOrderLineEntityList = new ArrayList<>();

        // 库存
        ShopStockOptDto shopStockOptDto = new ShopStockOptDto();

        //组装对象
        buildOrderContext(shopOrderApplyDto, shopOrderEntity, shopOrderLineEntityList, shopStockOptDto);

        // 分布式事务
        shopOrderEntity = applyOrderGlobalService.applyOrderGlobal(shopStockOptDto, shopOrderEntity, shopOrderLineEntityList);

        return shopOrderEntity;
    }

    /**
     * 组装订单对象
     *
     * @param shopOrderApplyDto
     * @param shopOrderEntity
     * @param shopOrderLineEntityList
     * @return : void
     * @author: hujunsong
     * @date: 2023/3/30 22:41
     */
    private void buildOrderContext(ShopOrderApplyDto shopOrderApplyDto, ShopOrderEntity shopOrderEntity,
                                   List<ShopOrderLineEntity> shopOrderLineEntityList,
                                   ShopStockOptDto shopStockOptDto) {
        long id = SnowflakeIdGenerator.nextId();
        String orderNo = "ON" + id;

        BigDecimal totalAmountPayable = BigDecimal.ZERO;
        BigDecimal totalAmountDiscount = BigDecimal.ZERO;
        List<ShopStockOptDetailDto> shopStockOptDetailDtoList = new ArrayList<>();

        List<ShopOrderApplyLineDto> shopOrderApplyLineDtoList = shopOrderApplyDto.getShopOrderLineDtoList();
        for (int i = 0; i < shopOrderApplyLineDtoList.size(); i++) {
            long lineId = SnowflakeIdGenerator.nextId();
            String orderLineNo = "OLN" + lineId;
            ShopOrderLineEntity shopOrderLineEntity = new ShopOrderLineEntity();
            shopOrderLineEntity.setId(lineId);
            shopOrderLineEntity.setOrderLineNo(orderLineNo);
            shopOrderLineEntity.setOrderNo(orderNo);
            shopOrderLineEntity.setUserNo(shopOrderApplyDto.getUserNo());
            shopOrderLineEntity.setSkuNo(shopOrderApplyLineDtoList.get(i).getSkuNo());
            shopOrderLineEntity.setSkuName(shopOrderApplyLineDtoList.get(i).getSkuName());
            shopOrderLineEntity.setQuantity(shopOrderApplyLineDtoList.get(i).getQuantity());
            shopOrderLineEntity.setPrice(shopOrderApplyLineDtoList.get(i).getPrice());
            shopOrderLineEntity.setAmountPayable(shopOrderApplyLineDtoList.get(i).getAmountPayable());
            shopOrderLineEntity.setAmountActual(BigDecimal.ZERO);
            shopOrderLineEntity.setAmountDiscount(shopOrderApplyLineDtoList.get(i).getAmountDiscount());
//            shopOrderLineEntity.setDateCreate();
//            shopOrderLineEntity.setDateUpdate();
//            shopOrderLineEntity.setCreator();
//            shopOrderLineEntity.setUpdater();


            //总应付
            totalAmountPayable = totalAmountPayable.add(shopOrderApplyLineDtoList.get(i).getAmountPayable());
            //总优惠
            totalAmountDiscount = totalAmountDiscount.add(shopOrderApplyLineDtoList.get(i).getAmountDiscount());
            //订单行
            shopOrderLineEntityList.add(shopOrderLineEntity);

            ShopStockOptDetailDto shopStockLockDto = new ShopStockOptDetailDto();
            shopStockLockDto.setSkuNo(shopOrderApplyLineDtoList.get(i).getSkuNo());
            shopStockLockDto.setNums(shopOrderApplyLineDtoList.get(i).getQuantity());
            //库存锁定
            shopStockOptDetailDtoList.add(shopStockLockDto);
        }

        //库存操作对象
        shopStockOptDto.setShopStockOptDetailDtoList(shopStockOptDetailDtoList);
        shopStockOptDto.setFlowNo(orderNo);

        shopOrderEntity.setId(id);
        shopOrderEntity.setOrderNo(orderNo);
        shopOrderEntity.setUserNo(shopOrderApplyDto.getUserNo());
        shopOrderEntity.setOrderStatus(OrderStatusEnum.apply_unpaid.getCode());
        shopOrderEntity.setAmountActual(BigDecimal.ZERO);
        shopOrderEntity.setAmountPayable(totalAmountPayable);
        shopOrderEntity.setAmountDiscount(totalAmountDiscount);
//        shopOrderEntity.setDatePaid();
//        shopOrderEntity.setDateShipped();
//        shopOrderEntity.setCourierCompany();
//        shopOrderEntity.setCourierNo();
//        shopOrderEntity.setDateReceived();
//        shopOrderEntity.setCancelDate();
//        shopOrderEntity.setCanceler();
//        shopOrderEntity.setCancelReason();
//        shopOrderEntity.setLockDate();
//        shopOrderEntity.setLocker();
//        shopOrderEntity.setLockReason();
//        shopOrderEntity.setCreateDate();
//        shopOrderEntity.setUpdateDate();
//        shopOrderEntity.setCreator();
//        shopOrderEntity.setUpdater();
    }

    /**
     * 根据订单号查询
     *
     * @param orderNo
     * @return : com.sara.order.entity.ShopOrderEntity
     * @author: hujunsong
     * @date: 2023/3/30 20:27
     */
    @Override
    public ShopOrderEntity queryByOrderNo(String orderNo) {
        return shopOrderDao.queryByOrderNo(orderNo);
    }

    /**
     * 根据用户号查询
     *
     * @param userNo
     * @return : java.util.List<com.sara.order.entity.ShopOrderEntity>
     * @author: hujunsong
     * @date: 2023/3/30 20:29
     */
    @Override
    public List<ShopOrderEntity> queryByUserNo(String userNo) {
        return shopOrderDao.queryByUserNo(userNo);
    }
}
