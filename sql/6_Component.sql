SET character_set_client = utf8;
SET character_set_server = utf8;
SET character_set_connection = utf8;
SET character_set_database = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;
SET collation_database = utf8_general_ci;
SET collation_server = utf8_general_ci;

USE `fly-feel`;

-- --------------
-- 组件表
-- --------------

drop table if exists ff_component;
create table ff_component
(

    `id`             bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    `name`           varchar(64)         not null comment '组件名称',
    `description`    varchar(256)        not null default '' comment '组件描述',
    `parent_id`      bigint(20) unsigned not null default 0 comment '父组件id',-- 0为根组件
    `path`           varchar(256)        not null default '/' comment '路由地址',
    `component_type` tinyint             not null default 0 comment '类型', -- 可根据需要自己指定
    `component_path` varchar(256)        not null default '/' comment '组件地址', -- 可根据需要自己指定
    `visible`        tinyint             not null default 1 comment '显示状态', -- 0为不显示 1为显示
    `icon`           varchar(256)        not null default '' comment '图标',
    `order`          tinyint             not null default 0 comment '同级优先级',

    `system`         tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`         tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`            tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`      bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time`    timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`      bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time`    timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`         varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`       bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '组件资源表';
