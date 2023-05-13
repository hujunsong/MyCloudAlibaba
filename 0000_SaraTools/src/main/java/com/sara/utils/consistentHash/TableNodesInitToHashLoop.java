package com.sara.utils.consistentHash;

import org.apache.shardingsphere.core.rule.ShardingRule;
import org.apache.shardingsphere.core.rule.TableRule;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.apache.shardingsphere.underlying.common.rule.DataNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.stream.Collectors;

/**
 * 在应用启动时初始化分表的表结点，提前进行hash计算
 *
 * @author: hujunsong
 * @date: 2023/5/1 07:17
 */
@Component
public class TableNodesInitToHashLoop {

    private static Logger logger = LoggerFactory.getLogger(TableNodesInitToHashLoop.class);

    @Resource
    private ShardingDataSource shardingDataSource;

    private HashMap<String, SortedMap<Long, String>> tableVirtualNodes = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            ShardingRule rule = shardingDataSource.getRuntimeContext().getRule();
            Collection<TableRule> tableRules = rule.getTableRules();
            ConsistentHashAlgorithm consistentHashAlgorithm = new ConsistentHashAlgorithm();
            for (TableRule tableRule : tableRules) {
                String logicTable = tableRule.getLogicTable();

                tableVirtualNodes.put(logicTable,
                        consistentHashAlgorithm.initNodesToHashLoop(
                                tableRule.getActualDataNodes()
                                        .stream()
                                        .map(DataNode::getTableName)
                                        .collect(Collectors.toList()))
                );
            }
        } catch (Exception e) {
            logger.error("分表节点初始化失败 {}", e);
        }
    }

    public HashMap<String, SortedMap<Long, String>> getTableVirtualNodes() {
        return tableVirtualNodes;
    }

    public ShardingDataSource getShardingDataSource() {
        return shardingDataSource;
    }

    public void setShardingDataSource(ShardingDataSource shardingDataSource) {
        this.shardingDataSource = shardingDataSource;
    }

    public void setTableVirtualNodes(HashMap<String, SortedMap<Long, String>> tableVirtualNodes) {
        this.tableVirtualNodes = tableVirtualNodes;
    }
}
