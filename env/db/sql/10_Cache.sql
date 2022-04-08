SET character_set_client = utf8;
SET character_set_server = utf8;
SET character_set_connection = utf8;
SET character_set_database = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;
SET collation_database = utf8_general_ci;
SET collation_server = utf8_general_ci;

USE `fly-feel`;

drop table if exists ff_cache;
create table ff_cache
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    `key`         varchar(256)        not null comment '键',
    value         BLOB                not null comment '验证码',
    expires       bigint(20) unsigned not null DEFAULT 0 comment '过期时长，默认永不过期',

    `system`      bit(1)              NOT NULL DEFAULT false COMMENT '是否为系统参数',
    `enable`      bit(1)              NOT NULL DEFAULT true COMMENT '可用状态',
    `del`         bit(1)              NOT NULL DEFAULT false COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '缓存表';