package io.gitee.define.mapper;

import io.gitee.define.entity.Log;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 日志
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public interface LogMapper extends BaseModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertOrUpdate(Log record);

    int insertOrUpdateSelective(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    int updateBatch(List<Log> list);

    int updateBatchSelective(List<Log> list);

    int batchInsert(@Param("list") List<Log> list);
}