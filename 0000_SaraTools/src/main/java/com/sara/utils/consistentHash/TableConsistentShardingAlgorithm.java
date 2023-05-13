package com.sara.utils.consistentHash;

import com.sara.utils.spring.SpringContextUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;

public class TableConsistentShardingAlgorithm implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {

    /**
     * 精确分片
     * 一致性hash算法
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {

        //获取已经初始化的分表节点
        TableNodesInitToHashLoop initTableNodesToHashLoop =
                SpringContextUtils.getBeanByClass(TableNodesInitToHashLoop.class);
        if (CollectionUtils.isEmpty(availableTargetNames)) {
            return shardingValue.getLogicTableName();
        }

        //这里主要为了兼容当联表查询时，如果两个表非关联表则
        //当对副表分表时shardingValue这里传递进来的依然是主表的名称，
        //但availableTargetNames中确是副表名称，所有这里要从availableTargetNames中匹配真实表
        ArrayList<String> availableTargetNameList = new ArrayList<>(availableTargetNames);
//        String logicTableName = availableTargetNameList.get(0).replaceAll("[^(a-zA-Z_)]", "");
        String logicTableName = availableTargetNameList.get(0)
                .substring(0, availableTargetNameList.get(0).lastIndexOf("_"));
        SortedMap<Long, String> tableHashNode =
                initTableNodesToHashLoop.getTableVirtualNodes().get(logicTableName);

        ConsistentHashAlgorithm consistentHashAlgorithm = new ConsistentHashAlgorithm(tableHashNode,
                availableTargetNames);

        return consistentHashAlgorithm.getNode(shardingValue.getValue());
    }

    public static void main(String[] args) {
        System.out.println("shop_account_00".replaceAll("[^(a-zA-Z_)]", ""));
    }

    /**
     * 范围查询规则
     * 可以根据实际场景进行修改
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<String> shardingValue) {
        return availableTargetNames;
    }
}
