package io.gitee.define.mapper;

import io.gitee.define.entity.RelateAccountDepart;

import java.util.List;

/**
 * $DESCRIPTION
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface RelateAccountDepartMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RelateAccountDepart record);

    int insertOrUpdate(RelateAccountDepart record);

    int insertOrUpdateSelective(RelateAccountDepart record);

    int insertSelective(RelateAccountDepart record);

    RelateAccountDepart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RelateAccountDepart record);

    int updateByPrimaryKey(RelateAccountDepart record);

    int updateBatch(List<RelateAccountDepart> list);

    int updateBatchSelective(List<RelateAccountDepart> list);

    int batchInsert(List<RelateAccountDepart> list);

}