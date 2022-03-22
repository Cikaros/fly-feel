package io.gitee.oauth.service.impl;

import io.gitee.define.entity.SecurityAccount;
import io.gitee.define.mapper.SecurityAccountMapper;
import io.gitee.define.service.impl.AccountServiceImpl;
import io.gitee.security.service.IAccountSecurityService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.stereotype.Service;

/**
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
@Service
public class AccountSecurityServiceImpl extends AccountServiceImpl implements IAccountSecurityService {

    public AccountSecurityServiceImpl(SecurityAccountMapper securityAccountMapper) {
        super(securityAccountMapper);
    }

    @Override
    public SecurityAccount findByUsername(String username) {
        return ((SecurityAccountMapper) accountMapper).selectByUsername(username);
    }
}
