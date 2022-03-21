package io.gitee.define.mapper;

import io.gitee.define.entity.Client;

import java.util.List;

/**
 * $DESCRIPTION
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface ClientMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    int insertOrUpdate(Client record);

    int insertOrUpdateSelective(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);

    int updateBatch(List<Client> list);

    int updateBatchSelective(List<Client> list);

    int batchInsert(List<Client> list);

}