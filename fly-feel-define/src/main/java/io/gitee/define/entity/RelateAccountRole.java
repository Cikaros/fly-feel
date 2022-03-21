package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 账户角色关联表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class RelateAccountRole extends BaseModel {
    /**
     * 关联账户id
     */
    private Long accountId;

    /**
     * 关联角色id
     */
    private Long roleId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}