package io.gitee.define.service.impl;

import io.gitee.core.service.BaseService;
import io.gitee.define.mapper.AccountMapper;
import io.gitee.define.mapper.SecurityAccountMapper;
import io.gitee.define.service.IAccountService;
import io.gitee.define.entity.Account;
import io.gitee.define.service.ILogger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * 账户基础操作业务
 *
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
@Service
public class AccountServiceImpl extends BaseService implements IAccountService, ILogger {

    protected final SecurityAccountMapper accountMapper;

    public AccountServiceImpl(SecurityAccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    private boolean updateDelByPrimaryKey(Long id, boolean del) {
        Account account = new Account();
        account.setId(id);
        account.setDel(del);
        int i = accountMapper.updateByPrimaryKey(account);
        log.debug(i18n.get("io.gitee.define.service.impl.AccountServiceImpl.updateDelByPrimaryKey", account, i == 1));
        return i == 1;
    }

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        return this.updateDelByPrimaryKey(id, true);
    }

    @Override
    public boolean restoreByPrimaryKey(Long id) {
        return this.updateDelByPrimaryKey(id, false);
    }

    @Override
    public boolean insertOrUpdate(Account record) {
        int i = accountMapper.insertOrUpdateSelective(record);
        log.debug(i18n.get("io.gitee.define.service.impl.AccountServiceImpl.insertOrUpdate", record, i == 1));
        return i == 1;
    }

    @Override
    public boolean insert(Account record) {
        int i = accountMapper.insertSelective(record);
        log.debug(i18n.get("io.gitee.define.service.impl.AccountServiceImpl.insert", record, i == 1));
        return i == 1;
    }

    @Override
    public boolean update(Account record) {
        int i = accountMapper.updateByPrimaryKeySelective(record);
        return i == 1;
    }

    @Override
    public boolean update(Collection<Account> list) {
        int i = accountMapper.updateBatchSelective(list);
        return i == list.size();
    }

    @Override
    public boolean batchInsert(Collection<Account> list) {
        int i = accountMapper.batchInsert(list);
        return i == list.size();
    }

    @Override
    public Account selectByPrimaryKey(Long id) {
        Account account = accountMapper.selectByPrimaryKey(id);
        Assert.notNull(account, i18n.get("io.gitee.define.service.impl.AccountServiceImpl.selectByPrimaryKey.null", id));
        return account;
    }

    @Override
    public Account findByUsername(String username) {
        Account account = accountMapper.findByUsername(username);
        Assert.notNull(account, i18n.get("io.gitee.define.service.impl.AccountServiceImpl.findByUsername.null", username));
        return account;
    }

    @Override
    public Account selectByUsername(String username) {
        Account account = accountMapper.selectByUsername(username);
        Assert.notNull(account, i18n.get("io.gitee.define.service.impl.AccountServiceImpl.selectByUsername.null", username));
        return account;
    }
}
