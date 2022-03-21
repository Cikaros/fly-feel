package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 三方客户端角色关联表
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class RelateClientRole extends BaseModel {
    /**
    * 客户端id
    */
    private Long clientId;

    /**
    * 角色id
    */
    private Long roleId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}