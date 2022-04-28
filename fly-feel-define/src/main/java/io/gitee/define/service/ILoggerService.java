package io.gitee.define.service;

import io.gitee.define.entity.Log;

import java.util.List;

/**
 * 统一日志处理业务
 *
 * @author Cikaros
 * @date 2022/4/7
 * @since v1.0
 */
public interface ILoggerService {

    /**
     * 新增日志
     *
     * @param log 日志信息
     */
    void insert(Log log);

    /**
     * 根据所传参数查询数据
     */
    List<Log> selectByLog(Log record);

    /**
     * 根据所传参数查询数据
     */
    Log selectByPrimaryKey(Long id);

}
