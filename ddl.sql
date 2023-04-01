create table sara_shop_account.shop_account
(
    id               bigint                                  not null comment '主键雪花ID'
        primary key,
    account_no       varchar(32)                             not null comment '账户号',
    user_no          varchar(32)                             not null comment '用户号',
    amount_remain    decimal(6, 2) default 0.00              not null comment '总余额',
    amount_available decimal(6, 2) default 0.00              not null comment '可用余额',
    amount_lock      decimal(6, 2) default 0.00              not null comment '锁定余额',
    flag             int           default 1                 not null comment '有效标识:0-无效,1-有效',
    date_create      datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    date_update      datetime      default CURRENT_TIMESTAMP not null comment '更新时间',
    creator          varchar(32)   default 'sys'             not null comment '创建人',
    updater          varchar(32)   default 'sys'             not null comment '更新人',
    constraint shop_account_account_no_uindex
        unique (account_no)
);

create index idx_shop_account_date_update
    on sara_shop_account.shop_account (date_update);

create index idx_shop_account_user_no
    on sara_shop_account.shop_account (user_no);

create table sara_shop_account.shop_account_detail
(
    id                bigint                                  not null comment '主键雪花id'
        primary key,
    account_detail_no varchar(32)                             not null comment '账户明细编号',
    account_no        varchar(32)                             not null comment '账户号',
    user_no           varchar(32)                             not null comment '用户号',
    amount_opt        decimal(6, 2) default 0.00              not null comment '操作金额',
    opt_type          varchar(8)                              not null comment '操作方式:add-增加余额,reduce-减少余额,lock-冻结余额',
    flow_no           varchar(32)                             not null comment '操作流水号',
    date_create       datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    date_update       datetime      default CURRENT_TIMESTAMP not null comment '更新时间',
    creator           varchar(32)   default 'sys'             not null comment '创建人',
    updater           varchar(32)   default 'sys'             not null comment '更新人',
    constraint uk_shop_account_detail_flow_no
        unique (flow_no),
    constraint uk_shop_account_detail_no
        unique (account_detail_no)
);

create index idx_shop_account_detail_account_no
    on sara_shop_account.shop_account_detail (account_no);

create index idx_shop_account_detail_date_update
    on sara_shop_account.shop_account_detail (date_update);

create index idx_shop_account_detail_user_no
    on sara_shop_account.shop_account_detail (user_no);

create table sara_shop_order.shop_order
(
    id              bigint                                  not null comment '主键雪花id'
        primary key,
    order_no        varchar(32)                             not null comment '订单号',
    user_no         varchar(32)                             not null comment '用户号',
    order_status    varchar(32)   default 'apply_unpaid'    not null comment '订单状态:apply_unpaid-已下单未支付,paid_unshipped-已支付未发货,locked-已锁定不发货,canceled-已取消未退款,refunded-已退款,shipped_unreceived-已发货待签收,received-已签收,rejected-已拒收',
    amount_payable  decimal(6, 2) default 0.00              not null comment '应付金额',
    amount_actual   decimal(6, 2) default 0.00              not null comment '实付金额',
    amount_discount decimal(6, 2) default 0.00              not null comment '优惠金额',
    date_paid       datetime                                null comment '支付时间',
    date_shipped    datetime                                null comment '发货时间',
    courier_company varchar(32)                             null comment '快递公司:SF-顺丰,YTO-圆通,EMS-邮政',
    courier_no      varchar(64)                             null comment '快递单号',
    date_received   datetime                                null comment '签收时间',
    cancel_date     datetime                                null comment '取消时间',
    canceler        varchar(32)                             null comment '取消人',
    cancel_reason   varchar(32)                             null comment '取消原因',
    lock_date       datetime                                null comment '锁定时间',
    locker          varchar(32)                             null comment '锁定人',
    lock_reason     varchar(32)                             null comment '锁定原因',
    create_date     datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_date     datetime      default CURRENT_TIMESTAMP not null comment '更新时间',
    creator         varchar(32)   default 'sys'             null comment '创建人',
    updater         varchar(32)   default 'sys'             null comment '更新人',
    constraint uk_shop_order_no
        unique (order_no)
);

create index idx_shop_order_create_date
    on sara_shop_order.shop_order (create_date);

create index idx_shop_order_date_paid
    on sara_shop_order.shop_order (date_paid);

create index idx_shop_order_date_shipped
    on sara_shop_order.shop_order (date_shipped);

create index idx_shop_order_update_date
    on sara_shop_order.shop_order (update_date);

create index idx_shop_order_user_no
    on sara_shop_order.shop_order (user_no);

create table sara_shop_order.shop_order_line
(
    id              bigint                                  not null comment '主键id'
        primary key,
    order_line_no   varchar(32)                             not null comment '订单行号',
    order_no        varchar(32)                             not null comment '订单号',
    user_no         varchar(32)                             not null comment '用户号',
    sku_no          varchar(32)                             not null comment '物料代码',
    sku_name        varchar(64)                             not null comment '商品名称',
    quantity        int           default 0                 not null comment '数量',
    price           decimal(6, 2) default 0.00              not null comment '单价',
    amount_payable  decimal(6, 2) default 0.00              null comment '应付金额',
    amount_actual   decimal(6, 2) default 0.00              not null comment '实付金额',
    amount_discount decimal(6, 2) default 0.00              not null comment '优惠金额',
    date_create     datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    date_update     datetime      default CURRENT_TIMESTAMP not null comment '更新时间',
    creator         varchar(32)   default 'sys'             not null comment '创建人',
    updater         varchar(32)   default 'sys'             not null comment '更新人',
    constraint uk_shop_order_line_no
        unique (order_line_no)
);

create index idx_shop_order_line_date_update
    on sara_shop_order.shop_order_line (date_update);

create index idx_shop_order_line_order_no
    on sara_shop_order.shop_order_line (order_no);

create index idx_shop_order_line_user_no
    on sara_shop_order.shop_order_line (user_no);

create table sara_shop_stock.shop_stock
(
    id              bigint                                not null comment '主键ID'
        primary key,
    stock_no        varchar(32) default ''                not null comment '库存编号',
    sku_no          varchar(32) default ''                not null comment '物料代码',
    stock_total     int         default 0                 not null comment '库存总数量',
    stock_lock      int         default 0                 not null comment '锁定数量',
    stock_available int         default 0                 not null comment '可用数量',
    flag            int         default 1                 not null comment '生效标识:0-生效,1-失效',
    date_create     datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    date_update     datetime    default CURRENT_TIMESTAMP not null comment '更新时间',
    creator         varchar(32) default 'sys'             not null comment '创建人',
    updater         varchar(32) default 'sys'             not null comment '更新人',
    constraint uk_shop_stock_no_uindex
        unique (stock_no)
)
    comment '库存表';

create index idx_shop_stock_date_update
    on sara_shop_stock.shop_stock (date_update);

create index idx_shop_stock_sku_no
    on sara_shop_stock.shop_stock (sku_no);

create table sara_shop_stock.shop_stock_detail
(
    id              bigint                                not null comment '主键id'
        primary key,
    stock_detail_no varchar(32)                           not null comment '库存明细编号',
    stock_no        varchar(32)                           not null comment '库存编号',
    sku_no          varchar(32)                           not null comment '物料代码',
    num_opt         int         default 0                 not null comment '操作数量',
    opt_type        varchar(8)                            not null comment '操作类型:add-增加,reduce-减少,lock-冻结,unlock-解冻',
    flow_no         varchar(32)                           not null comment '操作流水号',
    date_create     datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    date_update     datetime    default CURRENT_TIMESTAMP not null comment '更新时间',
    creator         varchar(32) default 'sys'             not null comment '创建人',
    updater         varchar(32) default 'sys'             not null comment '更新人',
    constraint uk_shop_stock_detail_no
        unique (stock_detail_no)
)
    comment '库存操作明细表';

create index idx_shop_stock_detail_flow_no
    on sara_shop_stock.shop_stock_detail (flow_no);

create index idx_shop_stock_detail_sku_no
    on sara_shop_stock.shop_stock_detail (sku_no);

create index idx_shop_stock_detail_stock_no
    on sara_shop_stock.shop_stock_detail (stock_no);

