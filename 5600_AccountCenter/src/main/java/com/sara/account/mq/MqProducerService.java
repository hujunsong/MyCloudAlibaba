package com.sara.account.mq;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 消息发送服务
 *
 * @author: hujunsong
 * @date: 18:42
 */
public interface MqProducerService {

    /**
     * @param data 消息信息
     * @param tags 主题的标签
     * @return : void
     * @author: hujunsong
     * @date: 2023/4/2 20:10
     */
    void sendRocket(String tags, Object data) throws JsonProcessingException;

    /**
     * @param data 消息信息
     * @param tags 主题的标签
     * @return : void
     * @author: hujunsong
     * @date: 2023/4/2 20:10
     */
    void asyncSendRocket(String tags, Object data) throws JsonProcessingException;
}
