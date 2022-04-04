drop table if exists ff_cache;
create table ff_cache
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    `key`         varchar(256)        not null comment '键',
    value         BLOB                not null comment '验证码',
    expires       bigint(20) unsigned not null DEFAULT -1 Comment '过期时长，默认永不过期',

    `system`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`         tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '缓存表';