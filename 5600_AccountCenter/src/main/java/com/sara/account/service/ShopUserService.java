package com.sara.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sara.account.dto.RegisterCodeSendDto;
import com.sara.account.dto.RegisterCodeVerifyDto;
import com.sara.account.entity.ShopUserEntity;
import com.sara.utils.response.CommonResult;

/**
 * 用户表(ShopUser)表服务接口
 *
 * @author hujunsong
 * @since 2023-04-01 19:33:24
 */
public interface ShopUserService {

    /**
     * @param userName
     * @return : com.sara.account.entity.ShopUserEntity
     * @author: hujunsong
     * @date: 2023/4/2 08:52
     */
    ShopUserEntity queryByUserName(String userName);

    /**
     * 发送用户注册短信验证码
     *
     * @param registerCodeSendDto
     * @return : java.lang.String
     * @author: hujunsong
     * @date: 2023/4/2 12:20
     */
    CommonResult<String> registerSendCode(RegisterCodeSendDto registerCodeSendDto) throws JsonProcessingException;

    /**
     * 验证用户注册短信验证码
     *
     * @param registerCodeVerifyDto
     * @return : com.sara.account.entity.ShopUserEntity
     * @author: hujunsong
     * @date: 2023/4/2 12:22
     */
    CommonResult<ShopUserEntity> registerVerifyCode(RegisterCodeVerifyDto registerCodeVerifyDto) throws JsonProcessingException;
}
