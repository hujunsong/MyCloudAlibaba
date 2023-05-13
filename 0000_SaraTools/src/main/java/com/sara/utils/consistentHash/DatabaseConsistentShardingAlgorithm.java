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

public class DatabaseConsistentShardingAlgorithm implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {

    /**
     * 精确分片
     * 一致性hash算法
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {

        //获取已经初始化的分表节点
        DatabaseNodesInitToHashLoop initDatabaseNodesToHashLoop =
                SpringContextUtils.getBeanByClass(DatabaseNodesInitToHashLoop.class);
        if (CollectionUtils.isEmpty(availableTargetNames)) {
            return shardingValue.getValue();
        }

        //这里主要为了兼容当联表查询时，如果两个表非关联表则
        //当对副表分表时shardingValue这里传递进来的依然是主表的名称，
        //但availableTargetNames中确是副表名称，所有这里要从availableTargetNames中匹配真实表
        ArrayList<String> availableTargetNameList = new ArrayList<>(availableTargetNames);
        String logicDatabaseName = availableTargetNameList.get(0)
                .substring(0, availableTargetNameList.get(0).lastIndexOf("-"));
        SortedMap<Long, String> databaseHashNode =
                initDatabaseNodesToHashLoop.getDatabaseVirtualNodes().get(logicDatabaseName);

        ConsistentHashAlgorithm consistentHashAlgorithm = new ConsistentHashAlgorithm(databaseHashNode,
                availableTargetNames);

        return consistentHashAlgorithm.getNode(shardingValue.getValue());
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
