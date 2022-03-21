package io.gitee.define.mapper;

import io.gitee.define.entity.Department;

import java.util.List;

/**
 * $DESCRIPTION
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface DepartmentMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertOrUpdate(Department record);

    int insertOrUpdateSelective(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    int updateBatch(List<Department> list);

    int updateBatchSelective(List<Department> list);

    int batchInsert(List<Department> list);

}