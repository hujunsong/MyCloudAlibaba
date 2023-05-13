package com.sara.utils.snowflake.nacos;

import java.util.concurrent.*;

/**
 * 雪花算法
 * 参考https://blog.csdn.net/lq18050010830/article/details/89845790
 */
public class SnowflakeIdWorker {

    /**
     * 设置一个时间初始值    2^41 - 1   差不多可以用69年
     */
    private final long twepoch = 1609430400000L;

    /**
     * 5位的机器id
     */
    private final long workerIdBits = 5L;

    /**
     * 5位的机房id
     */
    private final long datacenterIdBits = 5L;

    /**
     * 每毫秒内产生的id数 2 的 12次方
     */
    private final long sequenceBits = 12L;

    /**
     * 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 代表一毫秒内生成的多个id的最新序号  12位 4096 -1 = 4095 个
     */
    private long sequence = 0L;

    /**
     * 记录产生时间毫秒数，判断是否是同1毫秒
     */
    private long lastTimestamp = -1L;

    /**
     * 机器ID  2进制5位  32位减掉1位 31个
     */
    private long workerId;

    /**
     * 机房ID 2进制5位  32位减掉1位 31个
     */
    private long datacenterId;

    /**
     * https://blog.csdn.net/fengyuyeguirenenen/article/details/126986466
     * 如果并发量不高，别说毫秒，连秒内并发都少，所以序列基本上都是0，那生成的 ID 基本就都是偶数了。
     */
    private byte sequenceOffset;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }


    public SnowflakeIdWorker(long workerId, long datacenterId) {

        // 检查机房id和机器id是否超过31 不能小于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {

            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
     */
    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {

            System.err.printf(
                    "clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (lastTimestamp == timestamp) {

            //这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;
            //当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            // 时间戳改变，毫秒内序列重置，防止并发小的时候，生成的id总是偶数
            vibrateSequenceOffset();
            sequence = sequenceOffset;
            // 并发小的时候，这样生成的id总是偶数
            //sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    // 这个方法总是返回 0 或者 1
    private void vibrateSequenceOffset() {
        sequenceOffset = (byte) (~sequenceOffset & 1);
    }

    /**
     * 当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {

        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    //获取当前时间戳
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * main 测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1, 1);

        //Common Thread Pool
//        ExecutorService pool = new ThreadPoolExecutor(5, 200,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), new ThreadPoolExecutor.AbortPolicy());
//
//        for (int t = 0; t < 5; t++) {
//            pool.execute(() -> {
//                for (int i = 0; i < 1000; i++) {
//                    System.out.println(Thread.currentThread().getName() + " " + snowflakeIdWorker.nextId());
//                }
//            });
//        }
//        pool.shutdown();

        // 低并发下的奇偶性测试
        for (int i = 0; i < 30; i++) {
            System.out.println(snowflakeIdWorker.nextId());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}