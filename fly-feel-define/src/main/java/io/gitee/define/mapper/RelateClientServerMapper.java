package io.gitee.define.mapper;

import io.gitee.define.entity.RelateClientServer;

import java.util.List;

/**
 * $DESCRIPTION
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface RelateClientServerMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RelateClientServer record);

    int insertOrUpdate(RelateClientServer record);

    int insertOrUpdateSelective(RelateClientServer record);

    int insertSelective(RelateClientServer record);

    RelateClientServer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RelateClientServer record);

    int updateByPrimaryKey(RelateClientServer record);

    int updateBatch(List<RelateClientServer> list);

    int updateBatchSelective(List<RelateClientServer> list);

    int batchInsert(List<RelateClientServer> list);

}