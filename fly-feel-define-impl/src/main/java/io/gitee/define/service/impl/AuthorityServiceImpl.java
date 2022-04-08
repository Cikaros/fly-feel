package io.gitee.define.service.impl;

import io.gitee.core.service.BaseService;
import io.gitee.core.util.Assert;
import io.gitee.define.entity.Authority;
import io.gitee.define.mapper.AuthorityMapper;
import io.gitee.define.service.IAuthorityService;
import io.gitee.define.service.ILogger;
import org.springframework.stereotype.Service;

/**
 * 权限业务实现
 *
 * @author Cikaros
 * @date 2022/4/8
 */
@Service
public class AuthorityServiceImpl extends BaseService implements IAuthorityService, ILogger {

    protected final AuthorityMapper authorityMapper;

    public AuthorityServiceImpl(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    private boolean updateDelByPrimaryKey(Long id, boolean del) {
        Authority authority = new Authority();
        authority.setId(id);
        authority.setDel(del);
        int i = authorityMapper.updateByPrimaryKeySelective(authority);
        return i == 1;
    }

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        return updateDelByPrimaryKey(id, true);
    }

    @Override
    public boolean restoreByPrimaryKey(Long id) {
        return updateDelByPrimaryKey(id, false);
    }

    @Override
    public boolean insertOrUpdateSelective(Authority record) {
        int i = authorityMapper.insertOrUpdateSelective(record);
        return i == 1;
    }

    @Override
    public boolean insertSelective(Authority record) {
        int i = authorityMapper.insertSelective(record);
        return i == 1;
    }

    @Override
    public Authority selectByPrimaryKey(Long id) {
        Authority authority = authorityMapper.selectByPrimaryKey(id);
        Assert.notNull(authority, i18n.get("io.gitee.define.service.impl.AuthorityServiceImpl.selectByPrimaryKey.null", id));
        return null;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Authority record) {
        int i = authorityMapper.updateByPrimaryKeySelective(record);
        return i == 1;
    }
}
