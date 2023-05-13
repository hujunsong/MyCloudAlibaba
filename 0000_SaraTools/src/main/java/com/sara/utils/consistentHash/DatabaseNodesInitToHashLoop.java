package com.sara.utils.consistentHash;

import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.SortedMap;

/**
 * 在应用启动时初始化分库的库结点，提前进行hash计算
 *
 * @author: hujunsong
 * @date: 2023/5/1 07:17
 */
@Component
public class DatabaseNodesInitToHashLoop {

    private static Logger logger = LoggerFactory.getLogger(DatabaseNodesInitToHashLoop.class);

    @Resource
    private ShardingDataSource shardingDataSource;

    private HashMap<String, SortedMap<Long, String>> databaseVirtualNodes = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            ConsistentHashAlgorithm consistentHashAlgorithm = new ConsistentHashAlgorithm();
            shardingDataSource.getDataSourceMap().forEach((k, v) -> {
                databaseVirtualNodes.put(k.substring(0, k.lastIndexOf("-")),
                        consistentHashAlgorithm.initNodesToHashLoop(
                                shardingDataSource.getDataSourceMap().keySet())
                );
            });
        } catch (Exception e) {
            logger.error("分库节点初始化失败 {}", e);
        }
    }

    public HashMap<String, SortedMap<Long, String>> getDatabaseVirtualNodes() {
        return databaseVirtualNodes;
    }

    public ShardingDataSource getShardingDataSource() {
        return shardingDataSource;
    }

    public void setShardingDataSource(ShardingDataSource shardingDataSource) {
        this.shardingDataSource = shardingDataSource;
    }

    public void setDatabaseVirtualNodes(HashMap<String, SortedMap<Long, String>> databaseVirtualNodes) {
        this.databaseVirtualNodes = databaseVirtualNodes;
    }
}
