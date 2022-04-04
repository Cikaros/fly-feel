package io.gitee.define.mapper;

import io.gitee.define.entity.Account;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

/**
 * 基础账户Mapper
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
@CacheConfig(cacheNames = "io.gitee.define.entity.Account")
public interface AccountMapper extends BaseMapper {

    @CacheEvict(key = "#a0")
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    int insertSelective(Account record);

    @Cacheable(key = "#a0")
    Account selectByPrimaryKey(Long id);

    @CacheEvict(key = "#p0.id")
    int updateByPrimaryKeySelective(Account record);

    @CacheEvict(key = "#p0.id")
    int updateByPrimaryKey(Account record);

    @CacheEvict
    int updateBatch(List<Account> list);

    @CacheEvict
    int updateBatchSelective(List<Account> list);

    int batchInsert(List<Account> list);

}