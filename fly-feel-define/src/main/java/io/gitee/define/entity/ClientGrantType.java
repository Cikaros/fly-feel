package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;
import io.gitee.define.enums.GrantType;

/**
 * 三方客户端认证模式关联表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class ClientGrantType extends BaseModel {
    /**
     * 三方客户端id
     */
    private Long clientId;

    /**
     * 认证模式
     */
    private GrantType authorizedGrantType;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public GrantType getAuthorizedGrantType() {
        return authorizedGrantType;
    }

    public void setAuthorizedGrantType(GrantType authorizedGrantType) {
        this.authorizedGrantType = authorizedGrantType;
    }

}