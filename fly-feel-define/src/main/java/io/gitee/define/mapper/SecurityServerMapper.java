package io.gitee.define.mapper;

import io.gitee.define.entity.Server;

/**
 * Security扩充ServerMapper
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
public interface SecurityServerMapper extends ServerMapper {

    Server selectByClientId(Long clientId);
}
