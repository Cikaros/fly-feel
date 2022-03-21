package io.gitee.define.mapper;

import io.gitee.define.entity.Component;

import java.util.List;

/**
 * $DESCRIPTION
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface ComponentMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Component record);

    int insertOrUpdate(Component record);

    int insertOrUpdateSelective(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Component record);

    int updateByPrimaryKey(Component record);

    int updateBatch(List<Component> list);

    int updateBatchSelective(List<Component> list);

    int batchInsert(List<Component> list);

}