package io.gitee.define.mapper;

import io.gitee.define.entity.Server;

import java.util.List;

/**
 * 服务管理Mapper
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface ServerMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Server record);

    int insertOrUpdate(Server record);

    int insertOrUpdateSelective(Server record);

    int insertSelective(Server record);

    Server selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Server record);

    int updateByPrimaryKey(Server record);

    int updateBatch(List<Server> list);

    int updateBatchSelective(List<Server> list);

    int batchInsert(List<Server> list);

}