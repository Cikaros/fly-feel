package io.gitee.define.mapper;

import io.gitee.define.entity.Account;

import java.util.List;

/**
 * 基础账户Mapper
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface AccountMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int updateBatch(List<Account> list);

    int updateBatchSelective(List<Account> list);

    int batchInsert(List<Account> list);

}