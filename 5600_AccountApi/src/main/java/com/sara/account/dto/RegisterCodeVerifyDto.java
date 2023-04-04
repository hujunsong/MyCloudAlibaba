package com.sara.account.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

import static com.sara.account.dto.RegisterCodeSendDto.REGISTER_CODE_REDIS_KEY_PREFIX;

public class RegisterCodeVerifyDto implements Serializable {

    private static final long serialVersionUID = 1906215460178109370L;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    public String getRegisterCodeRedisKey() {
        return REGISTER_CODE_REDIS_KEY_PREFIX + this.email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
