package io.gitee.define.mapper;

import io.gitee.define.entity.RelateClientAuthority;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 三方客户端权限关联表
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public interface RelateClientAuthorityMapper extends BaseModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RelateClientAuthority record);

    int insertOrUpdate(RelateClientAuthority record);

    int insertOrUpdateSelective(RelateClientAuthority record);

    int insertSelective(RelateClientAuthority record);

    RelateClientAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RelateClientAuthority record);

    int updateByPrimaryKey(RelateClientAuthority record);

    int updateBatch(List<RelateClientAuthority> list);

    int updateBatchSelective(List<RelateClientAuthority> list);

    int batchInsert(@Param("list") List<RelateClientAuthority> list);
}