package com.sara.account.mq.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sara.account.email.EmailSendService;
import com.sara.account.model.RegisterRedisCacheDomain;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.sara.account.mq.impl.MqProducerServiceImpl.SARA_SHOP_CONSUMER_GROUP;
import static com.sara.account.mq.impl.MqProducerServiceImpl.SARA_SHOP_TOPIC;
import static com.sara.account.service.impl.ShopUserServiceImpl.REGISTER_EMAIL_MQ_TOPIC_TAG;

/**
 * MqConsumerService
 *
 * @author: hujunsong
 * @date: 2023/4/2 21:23
 */
@Component
@RocketMQMessageListener(consumerGroup = SARA_SHOP_CONSUMER_GROUP, // 消费者组
        topic = SARA_SHOP_TOPIC, // top
        messageModel = MessageModel.CLUSTERING, //(集群消费模式 默认配置)
        consumeMode = ConsumeMode.CONCURRENTLY, //(异步多线程消费 默认配置)
        selectorType = SelectorType.TAG, // 按TAG过滤
        selectorExpression = REGISTER_EMAIL_MQ_TOPIC_TAG) // 指定TAG值
public class MqConsumerServiceImpl implements RocketMQListener<MessageExt> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EmailSendService emailSendService;

    @Override
    public void onMessage(MessageExt message) {
        logger.info("收到RockMQ消息，message={}", message);
        String json = new String(message.getBody());
        try {
            RegisterRedisCacheDomain registerRedisCacheDomain = new ObjectMapper().readValue(json, RegisterRedisCacheDomain.class);
            emailSendService.sendRegisterCodeEmail(registerRedisCacheDomain);
        } catch (JsonProcessingException e) {
            logger.error("RockMQ消息解析失败", e);
            e.printStackTrace();
        }
    }
}
