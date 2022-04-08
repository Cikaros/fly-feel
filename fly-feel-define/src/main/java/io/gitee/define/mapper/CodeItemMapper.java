package io.gitee.define.mapper;

import io.gitee.define.entity.CodeItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 代码项
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public interface CodeItemMapper extends BaseModelMapper {
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

    int batchInsert(@Param("list") List<CodeItem> list);
}