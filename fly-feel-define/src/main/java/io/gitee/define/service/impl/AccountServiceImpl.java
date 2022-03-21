package io.gitee.define.service.impl;

import io.gitee.core.service.BaseService;
import io.gitee.define.mapper.AccountMapper;
import io.gitee.define.service.IAccountService;
import io.gitee.define.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
@Service
public class AccountServiceImpl extends BaseService implements IAccountService {

    protected final AccountMapper accountMapper;

    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        Account account = new Account();
        account.setId(id);
        account.setDel(true);
        int i = accountMapper.updateByPrimaryKey(account);
        log.debug(i18n.get("io.gitee.define.service.impl.AccountServiceImpl.deleteByPrimaryKey", account, i == 1));
        return i == 1;
    }

    @Override
    public boolean restoreByPrimaryKey(Long id) {
        Account account = new Account();
        account.setId(id);
        account.setDel(false);
        int i = accountMapper.updateByPrimaryKey(account);
        log.debug(i18n.get("io.gitee.define.service.impl.AccountServiceImpl.restoreByPrimaryKey", account, i == 1));
        return i == 1;
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
    public Account findByPrimaryKey(Long id) {
        Account account = accountMapper.selectByPrimaryKey(id);
        Assert.notNull(account, i18n.get("io.gitee.define.service.impl.AccountServiceImpl.findByPrimaryKey.null", id));
        return account;
    }

    @Override
    public int update(Account record) {
        return 0;
    }

    @Override
    public int update(Collection<Account> list) {
        return 0;
    }

    @Override
    public int batchInsert(Collection<Account> list) {
        return 0;
    }
}
