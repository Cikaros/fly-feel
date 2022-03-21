package io.gitee.define.service;

import io.gitee.define.entity.Authority;

/**
 * 权限业务类
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface IAuthorityService {

    /**
     * 删除无用的权限
     *
     * @param id 主键
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
     * 新增或修改权限
     *
     * @param record 权限数据
     * @return 结果
     */
    boolean insertOrUpdateSelective(Authority record);

    /**
     * 新增权限
     *
     * @param record 权限数据
     * @return 结果
     */
    boolean insertSelective(Authority record);

    /**
     * 根据主键查询数据
     *
     * @param id 主键
     * @return 查询到的权限数据
     */
    Authority selectByPrimaryKey(Long id);

    /**
     * 更新权限
     *
     * @param record 权限数据
     * @return 结果
     */
    boolean updateByPrimaryKeySelective(Authority record);


}