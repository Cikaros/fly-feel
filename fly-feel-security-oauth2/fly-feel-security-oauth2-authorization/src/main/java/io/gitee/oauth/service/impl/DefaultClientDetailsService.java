package io.gitee.oauth.service.impl;

import io.gitee.core.service.BaseService;
import io.gitee.define.entity.SecurityClient;
import io.gitee.oauth.service.IClientSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 默认的ClientDetailService
 *
 * @author Cikaros
 * @date 2022/3/19
 * @since v1.0
 */
@Service
public class DefaultClientDetailsService extends BaseService implements ClientDetailsService {

    private IClientSecurityService service;

    @Autowired
    public void setService(IClientSecurityService service) {
        this.service = service;
    }

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        SecurityClient client = service.findByClientId(s);
        if (Objects.isNull(client))
            throw new ClientRegistrationException(s);
        return client;
    }
}
