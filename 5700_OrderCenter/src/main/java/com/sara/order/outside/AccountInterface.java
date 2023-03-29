package com.sara.order.outside;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.sara.utils.response.CommonResult;
import com.sara.account.dto.ShopAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * nacos 服务 id
 */
@FeignClient(name = "AccountCenter", configuration = NacosRule.class)
public interface AccountInterface {

    @PostMapping("/account/create")
    CommonResult<ShopAccountDto> createAccount(String userNo);
}