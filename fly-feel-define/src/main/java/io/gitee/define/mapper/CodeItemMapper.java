package io.gitee.define.mapper;

import io.gitee.define.entity.CodeItem;

import java.util.List;

/**
 * $DESCRIPTION
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface CodeItemMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CodeItem record);

    int insertOrUpdate(CodeItem record);

    int insertOrUpdateSelective(CodeItem record);

    int insertSelective(CodeItem record);

    CodeItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CodeItem record);

    int updateByPrimaryKey(CodeItem record);

    int updateBatch(List<CodeItem> list);

    int updateBatchSelective(List<CodeItem> list);

    int batchInsert(List<CodeItem> list);

}