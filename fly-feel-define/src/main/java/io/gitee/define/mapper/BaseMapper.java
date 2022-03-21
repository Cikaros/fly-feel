package io.gitee.define.mapper;

import io.gitee.core.entity.model.BaseModel;

/**
 * 基础Mapper
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface BaseMapper {

    boolean isExpires(BaseModel record);

    boolean isSystem(BaseModel record);

}
