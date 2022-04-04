package io.gitee.define.mapper;

import io.gitee.define.entity.Role;

import java.util.List;

/**
 * 角色管理Mapper
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface RoleMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertOrUpdate(Role record);

    int insertOrUpdateSelective(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int updateBatch(List<Role> list);

    int updateBatchSelective(List<Role> list);

    int batchInsert(List<Role> list);

}