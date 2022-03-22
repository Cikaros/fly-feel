package io.gitee.define.service.impl;

import io.gitee.define.entity.SecurityClient;
import io.gitee.define.mapper.SecurityClientMapper;
import io.gitee.define.service.impl.ClientServiceImpl;
import io.gitee.define.service.IClientSecurityService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 默认的ClientDetailsService实现
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
@Primary
@Service
public class ClientSecurityServiceImpl extends ClientServiceImpl implements IClientSecurityService {

    public ClientSecurityServiceImpl(SecurityClientMapper securityClientMapper) {
        super(securityClientMapper);
    }

    @Override
    public SecurityClient findByClientId(String clientId) {
        return ((SecurityClientMapper) clientMapper).selectByClientId(clientId);
    }
}
