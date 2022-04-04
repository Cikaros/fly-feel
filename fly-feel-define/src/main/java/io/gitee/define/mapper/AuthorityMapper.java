package io.gitee.define.mapper;

import io.gitee.define.entity.Authority;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 权限管理Mapper
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
@CacheConfig(cacheNames = "io.gitee.define.entity.Authority")
public interface AuthorityMapper extends BaseMapper {

    @CacheEvict(key = "#a0")
    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    int insertOrUpdate(Authority record);

    int insertOrUpdateSelective(Authority record);

    int insertSelective(Authority record);

    @Cacheable(key = "#a0")
    Authority selectByPrimaryKey(Long id);

    @CacheEvict(key = "#p0.id")
    int updateByPrimaryKeySelective(Authority record);

    @CacheEvict(key = "#p0.id")
    int updateByPrimaryKey(Authority record);

    @CacheEvict
    int updateBatch(List<Authority> list);

    @CacheEvict
    int updateBatchSelective(List<Authority> list);

    int batchInsert(List<Authority> list);

}