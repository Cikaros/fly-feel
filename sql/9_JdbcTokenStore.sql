Drop table if exists oauth_access_token;
create table oauth_access_token
(
    create_time       timestamp default now() comment '创建时间',
    token_id          VARCHAR(255) comment 'TokenId',
    token             BLOB comment 'Token值',
    authentication_id VARCHAR(255) comment '认证ID',
    user_name         VARCHAR(255) comment '用户名',
    client_id         VARCHAR(255) comment '客户端id',
    authentication    BLOB comment '认证数据',
    refresh_token     VARCHAR(255) comment '刷新TokenId'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

Drop table if exists oauth_refresh_token;
create table oauth_refresh_token
(
    create_time    timestamp default now() comment '创建时间',
    token_id       VARCHAR(255) comment '刷新TokenId',
    token          BLOB comment '刷新Token值',
    authentication BLOB comment '认证数据'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;