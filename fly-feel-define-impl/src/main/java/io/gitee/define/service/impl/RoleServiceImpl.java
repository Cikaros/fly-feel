package io.gitee.define.service.impl;

import io.gitee.core.service.BaseService;
import io.gitee.core.util.Assert;
import io.gitee.define.entity.Role;
import io.gitee.define.mapper.SecurityRoleMapper;
import io.gitee.define.service.ILogger;
import io.gitee.define.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色业务实现
 *
 * @author Cikaros
 * @date 2022/4/8
 */
@Service
public class RoleServiceImpl extends BaseService implements IRoleService, ILogger {

    protected final SecurityRoleMapper roleMapper;

    public RoleServiceImpl(SecurityRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    private boolean updateDelByPrimaryKey(Long id, boolean del) {
        Role role = new Role();
        role.setId(id);
        role.setDel(del);
        int i = roleMapper.updateByPrimaryKeySelective(role);
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
    public boolean insertOrUpdateSelective(Role record) {
        int i = roleMapper.insertOrUpdateSelective(record);
        return i == 1;
    }

    @Override
    public boolean insertSelective(Role record) {
        int i = roleMapper.insertSelective(record);
        return i == 1;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        Assert.notNull(role, i18n.get("io.gitee.define.service.impl.RoleServiceImpl.selectByPrimaryKey.null", id));
        return role;
    }

    @Override
    public boolean updateByPrimaryKeySelective(Role record) {
        int i = roleMapper.updateByPrimaryKeySelective(record);
        return i == 1;
    }
}
