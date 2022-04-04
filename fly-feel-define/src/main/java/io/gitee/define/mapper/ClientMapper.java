package io.gitee.define.mapper;

import io.gitee.define.entity.Client;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 客户端管理Mapper
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
@CacheConfig(cacheNames = "io.gitee.define.entity.Client")
public interface ClientMapper extends BaseMapper {

    @CacheEvict(key = "#a0")
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    int insertOrUpdate(Client record);

    int insertOrUpdateSelective(Client record);

    int insertSelective(Client record);

    @Cacheable(key = "#a0")
    Client selectByPrimaryKey(Long id);

    @CacheEvict(key = "#p0.id")
    int updateByPrimaryKeySelective(Client record);

    @CacheEvict(key = "#p0.id")
    int updateByPrimaryKey(Client record);

    @CacheEvict
    int updateBatch(List<Client> list);

    @CacheEvict
    int updateBatchSelective(List<Client> list);

    int batchInsert(List<Client> list);

}