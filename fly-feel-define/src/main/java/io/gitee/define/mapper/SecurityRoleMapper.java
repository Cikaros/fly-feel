package io.gitee.define.mapper;

import io.gitee.define.entity.SecurityRole;

import java.util.Collection;

/**
 * Security扩充RoleMapper
 *
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
public interface SecurityRoleMapper extends RoleMapper {

    Collection<SecurityRole> selectByAccountId(Long accountId);
}
