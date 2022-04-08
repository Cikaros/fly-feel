package io.gitee.define.service;

import io.gitee.define.entity.Role;
import io.gitee.define.entity.SecurityRole;
import org.springframework.lang.NonNull;

/**
 * Security 角色业务
 *
 * @author Cikaros
 * @date 2022/4/8
 */
public interface IRoleSecurityService extends IRoleService {

    /**
     * 根据角色编码查找未删除的角色信息
     *
     * @param code 角色编码
     * @return 结果
     */
    Role findByCode(@NonNull String code);

    /**
     * 根据角色编码禁用角色
     *
     * @param id 主键
     * @return 结果
     */
    boolean disableByCode(@NonNull Long id);

    /**
     * 根据角色编码解除禁用
     *
     * @param id 主键
     * @return 结果
     */
    boolean enableByCode(@NonNull Long id);

    /**
     * 角色编码是否存在
     *
     * @param code 角色编码
     * @return 结果
     */
    boolean isExistByCode(@NonNull String code);
}
