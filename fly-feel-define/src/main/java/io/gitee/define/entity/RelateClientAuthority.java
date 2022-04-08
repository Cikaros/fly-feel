package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

import java.util.Date;

/**
 * 三方客户端权限关联表
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public class RelateClientAuthority extends BaseModel {
    /**
     * 客户端id
     */
    private Long clientId;

    /**
     * 权限id
     */
    private Long authorityId;


    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}