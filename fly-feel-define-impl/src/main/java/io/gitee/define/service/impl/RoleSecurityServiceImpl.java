package io.gitee.define.service.impl;

import io.gitee.core.util.Assert;
import io.gitee.define.entity.Role;
import io.gitee.define.mapper.SecurityRoleMapper;
import io.gitee.define.service.IRoleSecurityService;
import org.springframework.stereotype.Service;

/**
 * Security 角色业务实现
 *
 * @author Cikaros
 * @date 2022/4/8
 */
@Service
public class RoleSecurityServiceImpl extends RoleServiceImpl implements IRoleSecurityService {

    public RoleSecurityServiceImpl(SecurityRoleMapper roleMapper) {
        super(roleMapper);
    }

    @Override
    public Role findByCode(String code) {
        Role role = roleMapper.findByCode(code);
        Assert.notNull(role, i18n.get("io.gitee.define.service.impl.RoleSecurityServiceImpl.findByCode.null", code));
        return role;
    }

    private boolean updateEnableByPrimaryKey(Long id, boolean enable) {
        Role role = new Role();
        role.setId(id);
        role.setEnable(enable);
        int i = roleMapper.updateByPrimaryKeySelective(role);
        return i == 1;
    }

    @Override
    public boolean disableByCode(Long id) {
        return this.updateEnableByPrimaryKey(id, false);
    }

    @Override
    public boolean enableByCode(Long id) {
        return this.updateEnableByPrimaryKey(id, true);
    }

    @Override
    public boolean isExistByCode(String code) {
        return roleMapper.isExistByCode(code);
    }
}
