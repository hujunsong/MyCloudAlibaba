package com.sara.order.dao;

import com.sara.order.entity.ShopOrderLine;
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
    List<ShopOrderLine> listByOrderNo(@Param("orderNo") String orderNo);

    ShopOrderLine queryByOrderLineNo(@Param("orderLineNo") String orderLineNo);

    /**
     * 新增数据
     *
     * @param shopOrderLine 实例对象
     * @return 影响行数
     */
    int insert(ShopOrderLine shopOrderLine);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShopOrderLine> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ShopOrderLine> entities);
}

