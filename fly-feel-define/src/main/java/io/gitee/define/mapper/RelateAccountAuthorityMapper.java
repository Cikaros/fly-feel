package io.gitee.define.mapper;

import io.gitee.define.entity.RelateAccountAuthority;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 用户权限关联表
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public interface RelateAccountAuthorityMapper extends BaseModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RelateAccountAuthority record);

    int insertOrUpdate(RelateAccountAuthority record);

    int insertOrUpdateSelective(RelateAccountAuthority record);

    int insertSelective(RelateAccountAuthority record);

    RelateAccountAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RelateAccountAuthority record);

    int updateByPrimaryKey(RelateAccountAuthority record);

    int updateBatch(List<RelateAccountAuthority> list);

    int updateBatchSelective(List<RelateAccountAuthority> list);

    int batchInsert(@Param("list") List<RelateAccountAuthority> list);
}