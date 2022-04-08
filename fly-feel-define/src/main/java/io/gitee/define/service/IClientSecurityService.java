package io.gitee.define.service;

import io.gitee.define.entity.SecurityClient;

/**
 * Security 客户端业务
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
public interface IClientSecurityService extends IClientService {

    /**
     * 根据客户端Id查找未删除的客户端信息
     *
     * @param clientId 客户端Id
     * @return 结果
     */
    SecurityClient findByClientId(String clientId);
}
