package io.gitee.define.mapper;

import io.gitee.define.entity.RelateRoleAuthority;

import java.util.List;

/**
 * 角色权限管理Mapper
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface RelateRoleAuthorityMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RelateRoleAuthority record);

    int insertOrUpdate(RelateRoleAuthority record);

    int insertOrUpdateSelective(RelateRoleAuthority record);

    int insertSelective(RelateRoleAuthority record);

    RelateRoleAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RelateRoleAuthority record);

    int updateByPrimaryKey(RelateRoleAuthority record);

    int updateBatch(List<RelateRoleAuthority> list);

    int updateBatchSelective(List<RelateRoleAuthority> list);

    int batchInsert(List<RelateRoleAuthority> list);

}