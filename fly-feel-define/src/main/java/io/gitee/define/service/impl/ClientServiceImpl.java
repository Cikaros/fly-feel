package io.gitee.define.service.impl;

import io.gitee.core.service.BaseService;
import io.gitee.define.service.IClientService;
import io.gitee.define.entity.Client;
import io.gitee.define.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
@Service
public class ClientServiceImpl extends BaseService implements IClientService {

    protected final ClientMapper clientMapper;

    public ClientServiceImpl(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        Client account = new Client();
        account.setId(id);
        account.setDel(true);
        int i = clientMapper.updateByPrimaryKey(account);
        log.debug(i18n.get("io.gitee.define.service.impl.ClientServiceImpl.deleteByPrimaryKey", account, i == 1));
        return i == 1;
    }

    @Override
    public boolean restoreByPrimaryKey(Long id) {
        Client account = new Client();
        account.setId(id);
        account.setDel(false);
        int i = clientMapper.updateByPrimaryKey(account);
        log.debug(i18n.get("io.gitee.define.service.impl.ClientServiceImpl.restoreByPrimaryKey", account, i == 1));
        return i == 1;
    }

    @Override
    public boolean insertOrUpdate(Client record) {
        int i = clientMapper.insertOrUpdateSelective(record);
        log.debug(i18n.get("io.gitee.define.service.impl.ClientServiceImpl.insertOrUpdate", record, i == 1));
        return i == 1;
    }

    @Override
    public boolean insert(Client record) {
        int i = clientMapper.insertSelective(record);
        log.debug(i18n.get("io.gitee.define.service.impl.ClientServiceImpl.insert", record, i == 1));
        return i == 1;
    }

    @Override
    public Client findByPrimaryKey(Long id) {
        Client account = clientMapper.selectByPrimaryKey(id);
        Assert.notNull(account, i18n.get("io.gitee.define.service.impl.ClientServiceImpl.findByPrimaryKey.null", id));
        return account;
    }

    @Override
    public boolean update(Client record) {
        return false;
    }

    @Override
    public boolean update(Collection<Client> list) {
        return false;
    }

    @Override
    public boolean batchInsert(Collection<Client> list) {
        return false;
    }
}
