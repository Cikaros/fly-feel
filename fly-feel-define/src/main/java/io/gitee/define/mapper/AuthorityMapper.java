package io.gitee.define.mapper;

import io.gitee.define.entity.Authority;

import java.util.List;

/**
 * $DESCRIPTION
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface AuthorityMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    int insertOrUpdate(Authority record);

    int insertOrUpdateSelective(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    int updateBatch(List<Authority> list);

    int updateBatchSelective(List<Authority> list);

    int batchInsert(List<Authority> list);

}