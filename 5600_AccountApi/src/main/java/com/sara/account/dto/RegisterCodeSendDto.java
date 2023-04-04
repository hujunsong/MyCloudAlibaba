package com.sara.account.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.beans.Transient;
import java.io.Serializable;

public class RegisterCodeSendDto implements Serializable {

    private static final long serialVersionUID = 8808027433230549302L;

    public static final String REGISTER_CODE_REDIS_KEY_PREFIX = "register_code_info_";

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String loginPassword;

    /**
     * 发验证码保存的验证码（redisKey）
     * key: register_code_info_234390012@qq.com
     * value: {"code":"验证码",{"userName":"hujunsong","email": "234390012@qq.com","loginPassword":"123456"}}
     *
     * @return : java.lang.String
     * @author: hujunsong
     * @date: 2023/4/2 11:43
     */
    @Transient
    public String getRegisterCodeRedisKey() {
        return REGISTER_CODE_REDIS_KEY_PREFIX + getEmail();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
