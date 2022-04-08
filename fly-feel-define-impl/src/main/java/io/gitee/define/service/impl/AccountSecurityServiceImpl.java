package io.gitee.define.service.impl;

import io.gitee.core.exception.BaseModelException;
import io.gitee.core.util.Assert;
import io.gitee.define.entity.Account;
import io.gitee.define.entity.SecurityAccount;
import io.gitee.define.mapper.SecurityAccountMapper;
import io.gitee.define.service.IAccountSecurityService;
import org.springframework.stereotype.Service;

/**
 * Security 账户业务实现
 *
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
        return accountMapper.findByUsername(username);
    }

    /**
     * 根据用户名修改用户状态
     *
     * @param username 用户名
     * @param enable   状态
     * @return 结果
     */
    private boolean updateEnableByUsername(String username, boolean enable) {
        Account account = new Account();
        account.setUsername(username);
        account.setEnable(enable);
        int i = accountMapper.updateByUsername(account);
        return i == 1;
    }

    @Override
    public boolean disableByUsername(String username) {
        return updateEnableByUsername(username, false);
    }

    @Override
    public boolean enableByUsername(String username) {
        return updateEnableByUsername(username, true);
    }

    @Override
    public Account registered(Account account) {
        Assert.isTrue(!isExistByUsername(account.getUsername()), i18n.get("io.gitee.define.service.impl.AccountSecurityServiceImpl.isExistByUsername", account.getUsername()));
        int i = accountMapper.insertSelective(account);
        Assert.isTrue(i == 1, new BaseModelException(account));
        return account;
    }

    @Override
    public boolean isExistByUsername(String username) {
        return accountMapper.isExistByUsername(username);
    }
}
