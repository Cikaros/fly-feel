SET character_set_client = utf8;
SET character_set_server = utf8;
SET character_set_connection = utf8;
SET character_set_database = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;
SET collation_database = utf8_general_ci;
SET collation_server = utf8_general_ci;

USE `fly-feel`;

drop table if exists ff_log;
create table ff_log
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    `type`        tinyint             not null comment '日志类型',
    msg           BLOB                not null comment '日志信息',
    triggers      bigint(20) unsigned NOT NULL default 0 COMMENT '日志触发者,默认为系统触发',
    location      varchar(512)        NOT NULL COMMENT '触发地址',

    `system`      bit(1)              NOT NULL DEFAULT false COMMENT '是否为系统参数',
    `enable`      bit(1)              NOT NULL DEFAULT true COMMENT '可用状态',
    `del`         bit(1)              NOT NULL DEFAULT false COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '日志表';