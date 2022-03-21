package io.gitee.define.service;

import io.gitee.define.entity.Role;

/**
 * 角色业务类
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface IRoleService {

    /**
     * 根据主键删除无用的角色
     *
     * @param id
     * @return 结果
     */
    boolean deleteByPrimaryKey(Long id);

    /**
     * 根据主键回复数据
     *
     * @param id 主键
     * @return 结果
     */
    boolean restoreByPrimaryKey(Long id);

    /**
     * 插入或更新角色
     *
     * @param record 角色信息
     * @return 结果
     */
    boolean insertOrUpdateSelective(Role record);

    /**
     * 插入新角色
     *
     * @param record 角色信息
     * @return 结果
     */
    boolean insertSelective(Role record);

    /**
     * 根据id查询角色
     *
     * @param id 主键
     * @return 角色
     */
    Role selectByPrimaryKey(Long id);

    /**
     * 修改角色
     *
     * @param record 角色信息
     * @return 结果
     */
    boolean updateByPrimaryKeySelective(Role record);

}