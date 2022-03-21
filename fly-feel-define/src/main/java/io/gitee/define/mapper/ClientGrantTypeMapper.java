package io.gitee.define.mapper;

import io.gitee.define.entity.ClientGrantType;

import java.util.List;

/**
 * $DESCRIPTION
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface ClientGrantTypeMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClientGrantType record);

    int insertOrUpdate(ClientGrantType record);

    int insertOrUpdateSelective(ClientGrantType record);

    int insertSelective(ClientGrantType record);

    ClientGrantType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClientGrantType record);

    int updateByPrimaryKey(ClientGrantType record);

    int updateBatch(List<ClientGrantType> list);

    int updateBatchSelective(List<ClientGrantType> list);

    int batchInsert(List<ClientGrantType> list);

}