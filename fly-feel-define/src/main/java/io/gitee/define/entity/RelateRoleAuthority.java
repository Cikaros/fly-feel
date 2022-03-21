package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 角色权限关联表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class RelateRoleAuthority extends BaseModel {
    /**
     * 关联角色id
     */
    private Long roleId;

    /**
     * 关联权限id
     */
    private Long authorityId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}