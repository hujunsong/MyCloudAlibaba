package com.sara.utils;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SnowflakeId + Nacos
 */
@Component
public class SnowflakeIdGenerator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private NacosServiceManager nacosServiceManager;

    @Resource
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    private static SnowflakeIdWorker snowflakeIdWorker;

    private static int nodeId;

    @PostConstruct
    public void run() throws Exception {
        init();
    }

    /**
     * 获取雪花Id
     *
     * @return
     */
    public static long nextId() {
        return snowflakeIdWorker.nextId();
    }

    /**
     * 获取当前节点Id
     *
     * @return
     */
    public static int nodeId() {
        return nodeId;
    }

    /**
     * 获取当前服务所有节点 + 增加服务监听
     *
     * @throws NacosException
     */
    private void init() throws NacosException {
        NamingService namingService = nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
        namingService.subscribe(nacosDiscoveryProperties.getService(), nacosDiscoveryProperties.getGroup(), event -> {
            if (-1 == nacosDiscoveryProperties.getPort()) {
                return;
            }
            nodeId = calcNodeId(((NamingEvent) event).getInstances());
            if (nodeId > 1024) {
                throw new IllegalArgumentException("Worker & Datacenter Id calc results exceed 1024");
            }
            long workerId = nodeId % 32;
            long datacenterId = (long) Math.floor((float) nodeId / 32);
            logger.info("nodeId:" + nodeId + " workerId:" + workerId + " datacenterId:" + datacenterId);
            snowflakeIdWorker = new SnowflakeIdWorker(workerId, datacenterId);
        });
    }

    /**
     * 用ip+port计算服务列表的索引
     *
     * @param instanceList
     * @return
     */
    private int calcNodeId(List<Instance> instanceList) {
        List<Long> ipPosrList = instanceList.stream()
                .map(x -> dealIpPort(x.getIp(), x.getPort()))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        return ipPosrList.indexOf(dealIpPort(nacosDiscoveryProperties.getIp(), nacosDiscoveryProperties.getPort()));
    }

    /**
     * ip补0 + 端口号
     *
     * @param ip
     * @param port
     * @return
     */
    private static Long dealIpPort(String ip, int port) {
        String[] ips = ip.split("\\.");
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < ips.length; i++) {
            sbr.append(new DecimalFormat("000").format(Integer.parseInt(ips[i])));
        }
        return Long.parseLong(sbr.toString() + port);
    }

}