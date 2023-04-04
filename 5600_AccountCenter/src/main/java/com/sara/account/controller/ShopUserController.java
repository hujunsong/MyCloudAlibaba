package com.sara.account.controller;

import com.sara.account.dto.RegisterCodeSendDto;
import com.sara.account.dto.RegisterCodeVerifyDto;
import com.sara.account.dto.ShopUserDto;
import com.sara.account.entity.ShopUserEntity;
import com.sara.account.func.UserInterface;
import com.sara.account.service.ShopUserService;
import com.sara.account.utils.PojoConverter;
import com.sara.utils.response.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 用户接口
 *
 * @author: hujunsong
 * @date: 2023/4/1 19:55
 */
@RestController
public class ShopUserController implements UserInterface {

    @Resource
    private ShopUserService shopUserService;

    /**
     * 根据用户号查询
     *
     * @param userName 用户名
     * @return : com.sara.utils.response.CommonResult<com.sara.account.entity.ShopUserEntity>
     * @author: hujunsong
     * @date: 2023/4/1 20:06
     */
    @PostMapping("/user/queryByUserName")
    public CommonResult<ShopUserDto> queryByUserName(@RequestParam @NotBlank String userName) {
        try {
            ShopUserEntity shopUserEntity = this.shopUserService.queryByUserName(userName);
            return new CommonResult<ShopUserDto>().success(PojoConverter.shopUserEntity2Dto(shopUserEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopUserDto>().fail(exception.getMessage());
        }
    }

    /**
     * 发送邮箱验证码(用户注册)
     *
     * @param registerCodeSendDto
     * @return : com.sara.utils.response.CommonResult<com.sara.account.dto.ShopUserDto>
     * @author: hujunsong
     * @date: 2023/4/2 11:53
     */
    @PostMapping("/user/registerSendCode")
    public CommonResult<String> registerSendCode(@RequestBody @Valid RegisterCodeSendDto registerCodeSendDto) {
        try {
            return this.shopUserService.registerSendCode(registerCodeSendDto);
        } catch (Exception exception) {
            return new CommonResult<String>().fail().setMsg(exception.getMessage());
        }
    }

    /**
     * 验证邮箱验证码(用户注册)
     *
     * @param registerCodeVerifyDto
     * @return : com.sara.utils.response.CommonResult<java.lang.Void>
     * @author: hujunsong
     * @date: 2023/4/1 20:16
     */
    @PostMapping("/user/registerVerifyCode")
    public CommonResult<ShopUserDto> registerVerifyCode(@RequestBody @Valid RegisterCodeVerifyDto registerCodeVerifyDto) {
        try {
            CommonResult<ShopUserEntity> shopUserEntityCommonResult = this.shopUserService.registerVerifyCode(registerCodeVerifyDto);
            return new CommonResult<ShopUserDto>().success(PojoConverter.shopUserEntity2Dto(
                    shopUserEntityCommonResult.getData())).copyCodeAndMsg(shopUserEntityCommonResult);
        } catch (Exception exception) {
            return new CommonResult<ShopUserDto>().fail(exception.getMessage());
        }
    }
}

