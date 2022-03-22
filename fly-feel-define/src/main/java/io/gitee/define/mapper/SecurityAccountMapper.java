package io.gitee.define.mapper;

import io.gitee.define.entity.SecurityAccount;

/**
 * Security扩充AccountMapper
 *
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
public interface SecurityAccountMapper extends AccountMapper {

    SecurityAccount selectByUsername(String username);
}
