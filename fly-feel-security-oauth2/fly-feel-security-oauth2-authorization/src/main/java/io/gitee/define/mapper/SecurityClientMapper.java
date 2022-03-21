package io.gitee.define.mapper;

import io.gitee.define.entity.SecurityClient;

/**
 * Security扩充ClientMapper
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
public interface SecurityClientMapper extends ClientMapper {

    SecurityClient selectByClientId(String clientId);
}
