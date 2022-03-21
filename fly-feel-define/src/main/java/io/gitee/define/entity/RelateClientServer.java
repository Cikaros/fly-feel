package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 三方客户端资源服务器关联表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class RelateClientServer extends BaseModel {
    /**
     * 客户端id
     */
    private Long clientId;

    /**
     * 资源服务器id
     */
    private Long serverId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

}