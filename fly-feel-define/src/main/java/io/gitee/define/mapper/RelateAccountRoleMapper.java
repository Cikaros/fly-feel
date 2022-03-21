package io.gitee.define.mapper;

import io.gitee.define.entity.RelateAccountRole;

import java.util.List;

/**
 * $DESCRIPTION
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface RelateAccountRoleMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RelateAccountRole record);

    int insertOrUpdate(RelateAccountRole record);

    int insertOrUpdateSelective(RelateAccountRole record);

    int insertSelective(RelateAccountRole record);

    RelateAccountRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RelateAccountRole record);

    int updateByPrimaryKey(RelateAccountRole record);

    int updateBatch(List<RelateAccountRole> list);

    int updateBatchSelective(List<RelateAccountRole> list);

    int batchInsert(List<RelateAccountRole> list);

}