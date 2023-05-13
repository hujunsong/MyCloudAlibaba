package com.sara.utils.consistentHash;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashAlgorithm {

    //虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
    private SortedMap<Long, String> virtualNodes = new TreeMap<>();

    //当节点的数目很少时，容易造成数据的分布不均，所以增加虚拟节点来平均数据分布
    //虚拟节点的数目；虚拟节点的生成主要是用来让数据尽量均匀分布
    //虚拟节点是真实节点的不同映射而已
    //比如真实节点user1的hash值为100，那么我们增加3个虚拟节点user1-VN1、user1-VN2、user1-VN3，分别计算出来的hash值可能就为200，345，500；通过这种方式来将节点分布均匀
    private static final int VIRTUAL_NODES = 10;

    public ConsistentHashAlgorithm() {
    }

    public ConsistentHashAlgorithm(SortedMap<Long, String> virtualNodes, Collection<String> nodes) {
        if (Objects.isNull(virtualNodes)) {
            virtualNodes = initNodesToHashLoop(nodes);
        }
        this.virtualNodes = virtualNodes;
    }

    public SortedMap<Long, String> initNodesToHashLoop(Collection<String> nodes) {
        SortedMap<Long, String> virtualNodes = new TreeMap<>();
        for (String node : nodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String s = String.valueOf(i);
                String virtualNodeName = node + "->VN" + s;
                long hash = getHash(virtualNodeName);

                virtualNodes.put(hash, virtualNodeName);
            }
        }

        return virtualNodes;
    }

    /**
     * 通过计算key的hash
     * 计算映射的表节点
     *
     * @param key
     * @return
     */
    public String getNode(String key) {
        String virtualNode = getVirtualNode(key);
        //虚拟节点名称截取后获取真实节点
        if (StringUtils.isNotBlank(virtualNode)) {
            return virtualNode.substring(0, virtualNode.indexOf("->"));
        }
        return null;
    }

    /**
     * 获取虚拟节点
     *
     * @param key
     * @return
     */
    public String getVirtualNode(String key) {
        long hash = getHash(key);
        // 得到大于该Hash值的所有Map
        SortedMap<Long, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if (subMap.isEmpty()) {
            //如果没有比该key的hash值大的，则从第一个node开始
            Long i = virtualNodes.firstKey();
            //返回对应的服务器
            virtualNode = virtualNodes.get(i);
        } else {
            //第一个Key就是顺时针过去离node最近的那个结点
            Long i = subMap.firstKey();
            //返回对应的服务器
            virtualNode = subMap.get(i);
        }

        return virtualNode;
    }

    /**
     * 使用FNV1_32_HASH算法计算key的Hash值
     *
     * @param key
     * @return
     */
    public long getHash(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public static void main(String[] args) {
        System.out.println(new ConsistentHashAlgorithm().getHash("UN34529081208553434"));
        System.out.println(new ConsistentHashAlgorithm().getHash("CN34529081208553434"));
    }

    public SortedMap<Long, String> getVirtualNodes() {
        return virtualNodes;
    }
}
