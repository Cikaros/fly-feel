package io.gitee.define.mapper;

import io.gitee.define.entity.RelateClientRole;

import java.util.List;

/**
 * $DESCRIPTION
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface RelateClientRoleMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RelateClientRole record);

    int insertOrUpdate(RelateClientRole record);

    int insertOrUpdateSelective(RelateClientRole record);

    int insertSelective(RelateClientRole record);

    RelateClientRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RelateClientRole record);

    int updateByPrimaryKey(RelateClientRole record);

    int updateBatch(List<RelateClientRole> list);

    int updateBatchSelective(List<RelateClientRole> list);

    int batchInsert(List<RelateClientRole> list);

}