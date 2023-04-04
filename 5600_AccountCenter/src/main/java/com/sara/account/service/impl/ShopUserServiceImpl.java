package com.sara.account.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sara.account.dao.ShopAccountDao;
import com.sara.account.dao.ShopUserDao;
import com.sara.account.dto.RegisterCodeSendDto;
import com.sara.account.dto.RegisterCodeVerifyDto;
import com.sara.account.entity.ShopUserEntity;
import com.sara.account.model.RegisterRedisCacheDomain;
import com.sara.account.mq.MqProducerService;
import com.sara.account.service.ShopUserService;
import com.sara.utils.response.CommonResult;
import com.sara.utils.snowflake.nacos.SnowflakeIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 用户表(ShopUser)表服务实现类
 *
 * @author hujunsong
 * @since 2023-04-01 19:33:24
 */
@Service("shopUserService")
public class ShopUserServiceImpl implements ShopUserService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String REGISTER_EMAIL_MQ_TOPIC_TAG = "register_email_mq_topic_tag";

    @Resource
    private ShopUserDao shopUserDao;

    @Resource
    private ShopAccountDao shopAccountDao;

    @Resource
    private MqProducerService mqProducerService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * @param userName
     * @return : com.sara.account.entity.ShopUserEntity
     * @author: hujunsong
     * @date: 2023/4/2 08:52
     */
    @Override
    public ShopUserEntity queryByUserName(String userName) {
        return shopUserDao.queryByUserName(userName);
    }

    /**
     * 发送用户注册短信验证码
     *
     * @param registerCodeSendDto
     * @return : java.lang.String
     * @author: hujunsong
     * @date: 2023/4/2 12:20
     */
    @Override
    public CommonResult<String> registerSendCode(RegisterCodeSendDto registerCodeSendDto) throws JsonProcessingException {

        ShopUserEntity shopUserEntity = shopUserDao.queryByEmail(registerCodeSendDto.getEmail());
        if (shopUserEntity != null) {
            return new CommonResult<String>().fail().setMsg("邮箱已注册！");
        }
        // 生成验证码
        String code = String.valueOf((int) (Math.random() * 9 + 1) * Math.pow(10, 5));
        // redis缓存
        RegisterRedisCacheDomain registerRedisCacheDomain = new RegisterRedisCacheDomain();
        registerRedisCacheDomain.setEmail(registerCodeSendDto.getEmail());
        registerRedisCacheDomain.setCode(code);
        registerRedisCacheDomain.setRegisterCodeSendDto(registerCodeSendDto);

        boolean redisResult = redisTemplate.opsForValue().setIfAbsent(registerCodeSendDto.getRegisterCodeRedisKey(), new ObjectMapper().writeValueAsString(registerRedisCacheDomain), 5, TimeUnit.MINUTES);

        if (!redisResult) {
            logger.error("用户注册，缓存验证码失败,registerCodeSendDto={}", registerCodeSendDto);
            return new CommonResult<String>().success().setData("缓存验证码失败" + registerCodeSendDto.getEmail());
        }
        // 发送短信
        mqProducerService.sendRocket(REGISTER_EMAIL_MQ_TOPIC_TAG, registerRedisCacheDomain);
        return new CommonResult<String>().success().setData("验证码已发送至邮箱" + registerRedisCacheDomain.getEmail());
    }

    /**
     * 验证用户注册短信验证码
     *
     * @param registerCodeVerifyDto
     * @return : com.sara.account.entity.ShopUserEntity
     * @author: hujunsong
     * @date: 2023/4/2 12:22
     */
    @Override
    public CommonResult<ShopUserEntity> registerVerifyCode(RegisterCodeVerifyDto registerCodeVerifyDto) throws JsonProcessingException {

        //从redis拿到缓存的验证码和用户对象
        String json = (String) redisTemplate.opsForValue().get(registerCodeVerifyDto.getRegisterCodeRedisKey());
        RegisterRedisCacheDomain registerRedisCacheDomain = new ObjectMapper().readValue(json, RegisterRedisCacheDomain.class);
        if (registerRedisCacheDomain == null) {
            logger.error("用户注册，获取缓存验证码失败,registerRedisCacheDomain={}", registerRedisCacheDomain);
            return new CommonResult<ShopUserEntity>().fail("获取缓存验证码失败" + registerCodeVerifyDto.getEmail());
        }
        if (!registerRedisCacheDomain.getCode().equals(registerCodeVerifyDto.getVerifyCode())) {
            logger.error("用户注册，验证码错误,registerCodeVerifyDto={}", registerCodeVerifyDto);
            return new CommonResult<ShopUserEntity>().fail("验证码错误" + registerCodeVerifyDto);
        }
        long userId = SnowflakeIdGenerator.nextId();

        ShopUserEntity shopUserEntity = new ShopUserEntity();
        shopUserEntity.setId(userId);
        shopUserEntity.setUserNo("UN" + userId);
        shopUserEntity.setUserName(registerRedisCacheDomain.getRegisterCodeSendDto().getUserName());
//        shopUserEntity.setActualName();
//        shopUserEntity.setSex();
//        shopUserEntity.setIdNo();
        shopUserEntity.setEmail(registerRedisCacheDomain.getEmail());
//        shopUserEntity.setPhone();
        shopUserEntity.setLoginPassword(registerRedisCacheDomain.getRegisterCodeSendDto().getLoginPassword());
//        shopUserEntity.setPayPassword();
//        shopUserEntity.setDateCreate();
//        shopUserEntity.setDateUpdate();
//        shopUserEntity.setCreator();
//        shopUserEntity.setUpdater();
        shopUserDao.insert(shopUserEntity);

        long accountId = SnowflakeIdGenerator.nextId();
        shopAccountDao.createAccount(accountId, "AC" + accountId, "UN" + userId);
        shopUserEntity = shopUserDao.queryByUserNo(shopUserEntity.getUserNo());
        return new CommonResult<ShopUserEntity>().success(shopUserEntity);
    }
}
