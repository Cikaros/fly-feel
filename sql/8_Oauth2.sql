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
-- Oauth服务支持
-- --------------

drop table if exists ff_client;
create table ff_client
(
    `id`                     bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    client_id                varchar(64)         not null comment '客户端id',
    client_secret            varchar(64)         not null default '' comment '客户端密钥',
    redirect_uri             varchar(256)        not null default '' comment '重定向url',
    access_validity_seconds  integer             not null default 7200 comment 'token有效时长',
    refresh_validity_seconds integer             not null default 259200 comment '刷新token有效时长',
    scopes                    varchar(256)        not null default '' comment '数据范围',

    `system`                 tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`                 tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`                    tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`              bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time`            timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`              bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time`            timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`                 varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`               bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '三方客户端表';

drop table if exists ff_client_grant_type;
create table ff_client_grant_type
(
    `id`                  bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    client_id             bigint(20) unsigned not null comment '三方客户端id',
    authorized_grant_type tinyint             not null comment '认证模式',
    `system`              tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`              tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`                 tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`           bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time`         timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`           bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time`         timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`              varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`            bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '三方客户端认证模式关联表';

drop table if exists ff_relate_client_role;
create table ff_relate_client_role
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    client_id     bigint(20) unsigned not null comment '客户端id',
    role_id       bigint(20) unsigned not null comment '角色id',
    `system`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`         tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '三方客户端角色关联表';

drop table if exists ff_server;
create table ff_server
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    name          varchar(64)         not null comment '资源服务器名称',
    description   varchar(256)        not null default '' comment '资源服务器描述',
    resource_id   varchar(256)        not null comment '资源id',
    address       varchar(256)        not null default 'http://127.0.0.1' comment '资源服务器地址',
    `system`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`         tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '资源服务器';

drop table if exists ff_relate_client_server;
create table ff_relate_client_server
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    client_id     bigint(20) unsigned not null comment '客户端id',
    server_id     bigint(20) unsigned not null comment '资源服务器id',
    `system`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`         tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) comment '三方客户端资源服务器关联表';
