package com.sara.order.dao;

import com.sara.order.entity.ShopOrderLineEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单行(ShopOrderLine)表数据库访问层
 *
 * @author hujunsong
 * @since 2023-03-28 16:55:11
 */
public interface ShopOrderLineDao {

    /**
     * @param orderNo
     * @return
     */
    List<ShopOrderLineEntity> listByOrderNo(@Param("orderNo") String orderNo);

    ShopOrderLineEntity queryByOrderLineNo(@Param("orderLineNo") String orderLineNo);

    /**
     * 新增数据
     *
     * @param shopOrderLine 实例对象
     * @return 影响行数
     */
    int insert(ShopOrderLineEntity shopOrderLine);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShopOrderLine> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ShopOrderLineEntity> entities);
}

