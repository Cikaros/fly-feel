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
-- 部门
-- --------------
create table ff_department
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    `parent_by`   bigint(20)          NOT NULL DEFAULT 0 COMMENT '父部门ID',
    `top_id`      bigint(20)          NOT NULL DEFAULT 0 COMMENT '顶级父部门Id',
    `name`        varchar(64)         NOT NULL COMMENT '部门名称',
    `description` varchar(256)        NOT NULL DEFAULT '' COMMENT '组织描述',
    `icon`        varchar(256)        NOT NULL DEFAULT '' COMMENT '图标',
    `order`       tinyint(4)          NOT NULL DEFAULT 0 COMMENT '排序',

    `system`      bit(1)              NOT NULL DEFAULT false COMMENT '是否为系统参数',
    `enable`      bit(1)              NOT NULL DEFAULT true COMMENT '可用状态',
    `del`         bit(1)              NOT NULL DEFAULT false COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) COMMENT = '部门表';

create table ff_relate_account_depart
(
    `id`          bigint(20) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键',

    `account_id`  bigint(20) unsigned NOT NULL COMMENT '关联账户id',
    `depart_id`   bigint(20) unsigned NOT NULL COMMENT '关联部门id',

    `system`      bit(1)              NOT NULL DEFAULT false COMMENT '是否为系统参数',
    `enable`      bit(1)              NOT NULL DEFAULT true COMMENT '可用状态',
    `del`         bit(1)              NOT NULL DEFAULT false COMMENT '删除标记',
    `create_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '创建者ID',
    `create_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_by`   bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新者ID',
    `update_time` timestamp           NOT NULL DEFAULT NOW() COMMENT '更新时间',
    `remark`      varchar(512)        NOT NULL DEFAULT '' COMMENT '备注',
    `_version`    bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '版本号'
) COMMENT = '账户部门关联表';