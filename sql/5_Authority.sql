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
-- 权限
-- --------------

create table ff_authority
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    `name`        varchar(64)         NOT NULL COMMENT '权限名称',
    `code`        varchar(64)         NOT NULL COMMENT '权限编码',
    `description` varchar(256)        NOT NULL COMMENT '权限描述',

    `system`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`      tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`         tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) COMMENT = '权限表';

create table ff_relate_role_authority
(
    `id`           bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    `role_id`      bigint(20) unsigned NOT NULL COMMENT '关联角色id',
    `authority_id` bigint(20) unsigned NOT NULL COMMENT '关联权限id',

    `system`       tinyint(4)          NOT NULL DEFAULT 0 COMMENT '是否为系统参数',
    `enable`       tinyint(4)          NOT NULL DEFAULT 0 COMMENT '可用状态',
    `del`          tinyint(4)          NOT NULL DEFAULT 0 COMMENT '删除标记',
    `create_by`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time`  timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time`  timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`       varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`     bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) COMMENT = '角色权限关联表';