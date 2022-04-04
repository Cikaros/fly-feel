package io.gitee.define.mapper;

import io.gitee.define.entity.ClientGrantType;

/**
 * Security扩充GrantTypeMapper
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
public interface SecurityGrantTypeMapper extends ClientGrantTypeMapper {

    ClientGrantType selectByClientId(Long clientId);
}
