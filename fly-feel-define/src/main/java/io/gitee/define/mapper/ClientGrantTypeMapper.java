package io.gitee.define.mapper;

import io.gitee.define.entity.ClientGrantType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * GrantType管理Mapper
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
@CacheConfig(cacheNames = "io.gitee.define.entity.ClientGrantType")
public interface ClientGrantTypeMapper extends BaseMapper {

    @CacheEvict(key = "#a0")
    int deleteByPrimaryKey(Long id);

    int insert(ClientGrantType record);

    int insertOrUpdate(ClientGrantType record);

    int insertOrUpdateSelective(ClientGrantType record);

    int insertSelective(ClientGrantType record);

    @Cacheable(key = "#a0")
    ClientGrantType selectByPrimaryKey(Long id);

    @CacheEvict(key = "#p0.id")
    int updateByPrimaryKeySelective(ClientGrantType record);

    @CacheEvict(key = "#p0.id")
    int updateByPrimaryKey(ClientGrantType record);

    @CacheEvict
    int updateBatch(List<ClientGrantType> list);

    @CacheEvict
    int updateBatchSelective(List<ClientGrantType> list);

    int batchInsert(List<ClientGrantType> list);

}