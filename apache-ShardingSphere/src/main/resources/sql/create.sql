




create table `t_order` (
    `id` bigint(20) not null,
    `order_id` bigint(20) not null,
    `user_id` bigint(20) not null,
    `order_sn` varchar(255) default null comment '1',
    `member_id` varchar(255) default null comment '2',
    `create_time` datetime default null comment '3',
    `status` int(11) default null comment '4',
    primary key (`id`)
) engine=InnoDB default charset=utf8;

create table `t_order0` (
    `id` bigint(20) not null,
    `order_id` bigint(20) not null,
    `user_id` bigint(20) not null,
    `order_sn` varchar(255) default null comment '1',
    `member_id` varchar(255) default null comment '2',
    `create_time` datetime default null comment '3',
    `status` int(11) default null comment '4',
    primary key (`id`)
) engine=InnoDB default charset=utf8;

create table `t_order1` (
    `id` bigint(20) not null,
    `order_id` bigint(20) not null,
    `user_id` bigint(20) not null,
    `order_sn` varchar(255) default null comment '1',
    `member_id` varchar(255) default null comment '2',
    `create_time` datetime default null comment '3',
    `status` int(11) default null comment '4',
    primary key (`id`)
) engine=InnoDB default charset=utf8;

