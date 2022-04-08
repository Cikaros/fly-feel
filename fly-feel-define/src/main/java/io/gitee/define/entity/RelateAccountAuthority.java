package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

import java.util.Date;

/**
 * 用户权限关联表
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public class RelateAccountAuthority extends BaseModel {
    /**
     * 关联账户id
     */
    private Long accountId;

    /**
     * 关联权限id
     */
    private Long authorityId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

}