package io.gitee.security.service.impl;

import io.gitee.core.service.BaseService;
import io.gitee.define.entity.SecurityAccount;
import io.gitee.security.service.IAccountSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 默认的UserDetailService
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
@Service
public class DefaultUserDetailsService extends BaseService implements UserDetailsService {

    private IAccountSecurityService accountService;

    @Autowired
    public void setAccountService(IAccountSecurityService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SecurityAccount account = accountService.findByUsername(s);
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException(s);
        }
        return account;
    }
}
