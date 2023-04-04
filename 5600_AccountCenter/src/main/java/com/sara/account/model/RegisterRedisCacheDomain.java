package com.sara.account.model;

import com.sara.account.dto.RegisterCodeSendDto;

import java.io.Serializable;

/**
 * 用户注册发送短信缓存对象
 *
 * @author: hujunsong
 * @date: 2023/4/3 12:18
 */
public class RegisterRedisCacheDomain implements Serializable {

    private static final long serialVersionUID = -6448276739179794524L;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String code;

    /**
     * 用户信息
     */
    private RegisterCodeSendDto registerCodeSendDto;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RegisterCodeSendDto getRegisterCodeSendDto() {
        return registerCodeSendDto;
    }

    public void setRegisterCodeSendDto(RegisterCodeSendDto registerCodeSendDto) {
        this.registerCodeSendDto = registerCodeSendDto;
    }
}
