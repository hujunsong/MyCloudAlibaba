package com.sara.account.email;

import com.sara.account.model.RegisterRedisCacheDomain;

public interface EmailSendService {

    /**
     * 发送用户注册验证码邮件
     *
     * @param registerRedisCacheDomain
     * @return : void
     * @author: hujunsong
     * @date: 2023/4/3 15:37
     */
    void sendRegisterCodeEmail(RegisterRedisCacheDomain registerRedisCacheDomain);
}
