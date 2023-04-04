package com.sara.account.mq.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sara.account.mq.MqProducerService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息发送服务
 *
 * @author: hujunsong
 * @date: 2023/4/2 18:43
 */
@Component("mqProducerService")
public class MqProducerServiceImpl implements MqProducerService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String SARA_SHOP_TOPIC = "sara_shop_topic";
    public static final String SARA_SHOP_CONSUMER_GROUP = "SARA_SHOP_CONSUMER_GROUP";

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * @param data 消息信息
     * @param tags 主题的标签
     * @return : void
     * @author: hujunsong
     * @date: 2023/4/2 20:10
     */
    public void sendRocket(String tags, Object data) throws JsonProcessingException {
        String messageBodyStr = new ObjectMapper().writeValueAsString(data);
        rocketMQTemplate.convertAndSend(String.format("%s:%s", SARA_SHOP_TOPIC, tags), messageBodyStr);
    }

    /**
     * @param data 消息信息
     * @param tags 主题的标签
     * @return : void
     * @author: hujunsong
     * @date: 2023/4/2 20:10
     */
    public void asyncSendRocket(String tags, Object data) throws JsonProcessingException {

        String destination = String.format("%s:%s", SARA_SHOP_TOPIC, tags);
        String messageBodyStr = new ObjectMapper().writeValueAsString(data);
        rocketMQTemplate.asyncSend(destination, messageBodyStr, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("rocketMq发送成功,destination={}", destination);
            }

            @Override
            public void onException(Throwable throwable) {
                logger.info("rocketMq发送异常,destination={}", destination, throwable.getCause());
            }
        });
    }
}
