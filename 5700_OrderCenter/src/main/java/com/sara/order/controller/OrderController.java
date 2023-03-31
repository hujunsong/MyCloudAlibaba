package com.sara.order.controller;

import com.sara.order.dto.ShopOrderApplyDto;
import com.sara.order.dto.ShopOrderDto;
import com.sara.order.entity.ShopOrderEntity;
import com.sara.order.func.OrderInterface;
import com.sara.order.service.ShopOrderService;
import com.sara.order.utils.PojoConvertor;
import com.sara.utils.response.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 订单接口
 *
 * @author: hujunsong
 * @date: 2023/3/31 14:31
 */
@RestController
public class OrderController implements OrderInterface {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ShopOrderService shopOrderService;

    /**
     * 下单
     *
     * @param shopOrderApplyDto 下单对象
     * @return : com.sara.utils.response.CommonResult<com.sara.order.dto.ShopOrderDto>
     * @author: hujunsong
     * @date: 2023/3/29 18:55
     */
    @Override
    @ResponseBody
    @PostMapping("/order/apply")
    public CommonResult<ShopOrderDto> applyOrder(@RequestBody @Valid ShopOrderApplyDto shopOrderApplyDto) {
        logger.info("下单请求参数,shopOrderApplyDto={}", shopOrderApplyDto);
        try {
            ShopOrderEntity shopOrderEntity = shopOrderService.applyOrder(shopOrderApplyDto);
            return new CommonResult<ShopOrderDto>().success(PojoConvertor.shopOrderEntity2Dto(shopOrderEntity));
        } catch (Exception exception) {
            logger.error("下单异常,shopOrderApplyDto={}", shopOrderApplyDto, exception);
            return new CommonResult<ShopOrderDto>().fail();
        }
    }

    /**
     * 根据订单号查询
     *
     * @param orderNo 订单号
     * @return : com.sara.utils.response.CommonResult<com.sara.order.dto.ShopOrderDto>
     * @author: hujunsong
     * @date: 2023/3/29 19:01
     */
    @Override
    @ResponseBody
    @PostMapping("/order/queryByOrderNo")
    public CommonResult<ShopOrderDto> queryByOrderNo(String orderNo) {
        try {
            ShopOrderEntity shopOrderEntity = shopOrderService.queryByOrderNo(orderNo);
            return new CommonResult<ShopOrderDto>().success(PojoConvertor.shopOrderEntity2Dto(shopOrderEntity));
        } catch (Exception exception) {
            return new CommonResult<ShopOrderDto>().fail(exception.getMessage());
        }
    }

    /**
     * 根据用户号查询
     *
     * @param userNo
     * @return : com.sara.utils.response.CommonResult<List<ShopOrderDto>>
     * @author: hujunsong
     * @date: 2023/3/29 19:02
     */
    @Override
    @ResponseBody
    @PostMapping("/order/queryByUserNo")
    public CommonResult<List<ShopOrderDto>> queryByUserNo(String userNo) {
        try {
            List<ShopOrderEntity> shopOrderEntityList = shopOrderService.queryByUserNo(userNo);

            return new CommonResult<List<ShopOrderDto>>().success(
                    PojoConvertor.shopOrderEntityList2Dto(shopOrderEntityList));
        } catch (Exception exception) {
            return new CommonResult<List<ShopOrderDto>>().fail(exception.getMessage());
        }
    }
}