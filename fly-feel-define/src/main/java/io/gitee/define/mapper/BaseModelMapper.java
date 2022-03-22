package io.gitee.define.mapper;

import io.gitee.core.entity.form.BaseForm;
import io.gitee.core.entity.model.BaseModel;

/**
 * 基础模块Model
 *
 * @author Cikaros
 * @date 2022/3/16
 * @since v1.0
 */
public interface BaseModelMapper {

    /**
     * 查询基础BaseModel
     *
     * @param tableName 表名
     * @param query     查询条件
     * @return {@link BaseModel}
     */
    BaseModel selectByBaseModel(String tableName, BaseModel query);


    /**
     * 删除数据
     *
     * @param tableName  表名
     * @param primaryKey 主键
     * @return 删除结果
     */
    boolean deleteByPrimaryKey(String tableName, Long primaryKey);

    /**
     * 更新数据
     *
     * @param tableName 表名
     * @param form      更新数据Form
     * @return 更新结果
     */
    boolean updateByPrimaryKeySelective(String tableName, BaseForm form);

    /**
     * 数据是否过期
     *
     * @param tableName 表名
     * @param query     欲校验的数据
     * @return 校验结果
     */
    boolean isExpiresByBaseModel(String tableName, BaseModel query);

}
