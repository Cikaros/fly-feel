package io.gitee.define.mapper;

import io.gitee.define.entity.SecurityAuthority;

import java.util.Collection;

/**
 * Security扩充AuthorityMapper
 *
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
public interface SecurityAuthorityMapper extends AuthorityMapper {

    Collection<SecurityAuthority> selectByRoleId(Long id);
}
