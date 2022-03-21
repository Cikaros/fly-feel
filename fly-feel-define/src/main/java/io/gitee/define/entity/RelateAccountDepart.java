package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 账户部门关联表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class RelateAccountDepart extends BaseModel {
    /**
     * 关联账户id
     */
    private Long accountId;

    /**
     * 关联部门id
     */
    private Long departId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

}