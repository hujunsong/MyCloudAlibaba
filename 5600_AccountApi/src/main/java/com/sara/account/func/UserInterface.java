package com.sara.account.func;

import com.sara.account.dto.RegisterCodeSendDto;
import com.sara.account.dto.RegisterCodeVerifyDto;
import com.sara.account.dto.ShopUserDto;
import com.sara.utils.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 用户接口
 *
 * @author: hujunsong
 * @date: 2023/4/2 12:13
 */
@FeignClient(name = "UserCenter")
public interface UserInterface {

    /**
     * 根据用户号查询
     *
     * @param userName 用户名
     * @return : com.sara.utils.response.CommonResult<com.sara.account.entity.ShopUserEntity>
     * @author: hujunsong
     * @date: 2023/4/1 20:06
     */
    @PostMapping("/user/queryByUserName")
    CommonResult<ShopUserDto> queryByUserName(@RequestParam @NotBlank String userName);

    /**
     * 发送邮箱验证码(用户注册)
     *
     * @param registerCodeSendDto
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopUserDto>
     * @author: hujunsong
     * @date: 2023/4/2 11:53
     */
    @PostMapping("/user/registerSendCode")
    CommonResult<String> registerSendCode(@RequestBody @Valid RegisterCodeSendDto registerCodeSendDto);

    /**
     * 验证邮箱验证码(用户注册)
     *
     * @param registerCodeVerifyDto
     * @return : com.sara.utils.response.CommonResult<java.lang.Void>
     * @author: hujunsong
     * @date: 2023/4/1 20:16
     */
    @PostMapping("/user/registerVerifyCode")
    CommonResult<ShopUserDto> registerVerifyCode(@RequestBody @Valid RegisterCodeVerifyDto registerCodeVerifyDto);
}
