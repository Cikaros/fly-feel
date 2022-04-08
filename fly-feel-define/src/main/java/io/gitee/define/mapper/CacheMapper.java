package io.gitee.define.mapper;

import io.gitee.define.entity.Cache;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 缓存表
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public interface CacheMapper extends BaseModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cache record);

    int insertOrUpdate(Cache record);

    int insertOrUpdateSelective(Cache record);

    int insertSelective(Cache record);

    Cache selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cache record);

    int updateByPrimaryKey(Cache record);

    int updateBatch(List<Cache> list);

    int updateBatchSelective(List<Cache> list);

    int batchInsert(@Param("list") List<Cache> list);
}