package com.sara.order.func;

import com.sara.order.dto.ShopOrderApplyDto;
import com.sara.order.dto.ShopOrderDto;
import com.sara.utils.response.CommonResult;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description: 订单接口
 * @author: hujunsong
 * @date: 2023/3/29 18:30
 */
@FeignClient(name = "OrderCenter")
public interface OrderInterface {

    /**
     * 下单
     *
     * @param shopOrderApplyDto 下单对象
     * @return : com.sara.utils.response.CommonResult<com.sara.order.dto.ShopOrderDto>
     * @author: hujunsong
     * @date: 2023/3/29 18:55
     */
    @PostMapping("/order/apply")
    CommonResult<ShopOrderDto> applyOrder(@RequestBody @Valid ShopOrderApplyDto shopOrderApplyDto);

    /**
     * 根据订单号查询
     *
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.order.dto.ShopOrderDto>
     * @author: hujunsong
     * @date: 2023/3/29 19:01
     */
    @PostMapping("/order/queryByOrderNo")
    CommonResult<ShopOrderDto> queryByOrderNo(String orderNo);

    /**
     * 根据用户号查询
     *
     * @param userNo
     * @return : com.sara.utils.response.CommonResult<List<ShopOrderDto>>
     * @author: hujunsong
     * @date: 2023/3/29 19:02
     */
    @PostMapping("/order/queryByUserNo")
    CommonResult<List<ShopOrderDto>> queryByUserNo(String userNo);
}