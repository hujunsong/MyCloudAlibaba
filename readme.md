# 模块及功能划分
``` 
0000_SaraTools: 通用工具(基于nacos的雪花id)
5000_ShopGateway: 商城网关(从nginx接入,转发到各微服务)
5100_MarketCenter: 营销中心(活动,促营,优惠卷,团购,秒杀,拼团,众筹)
5600_AccountCenter: 账户中心(注册,登录,充值,金额记账)
5700_OrderCenter: 订单中心(下单,支付)
5800_StockCenter: 库存中心(上传商品,上架,下架,增加,减少库存)
5900_FulfillCenter: 履约中心(接单,分仓,发货,物流,客服,财务)
```
## 层级职能边界
```
controller: 出入参日志,参数校验,POJO转化
service: 业务处理
```
# Skywalking接入
```
# -javaagent后面是skywalking-agent.jar的路径
-javaagent:/usr/local/skywalking/skywalking-agent/skywalking-agent.jar
```
# Sentinel接入
```
-Dcsp.sentinel.dashboard.server=consoleIp:9091
```