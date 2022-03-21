package io.gitee.define.service;

import io.gitee.define.entity.Client;

import java.util.Collection;

/**
 * 客户端业务类
 *
 * @author Cikaros
 * @date 2022/3/20
 * @since v1.0
 */
public interface IClientService {

    /**
     * 根据主键删除数据
     *
     * @param id 主键
     * @return 结果
     */
    boolean deleteByPrimaryKey(Long id);

    /**
     * 根据主键回复数据
     *
     * @param id 主键
     * @return 结果
     */
    boolean restoreByPrimaryKey(Long id);

    /**
     * 根据所提供的客户端信息新增或修改数据
     *
     * @param record 客户端信息
     * @return 结果
     */
    boolean insertOrUpdate(Client record);

    /**
     * 根据所提供的客户端信息新增
     *
     * @param record 客户端信息
     * @return 结果
     */
    boolean insert(Client record);

    /**
     * 根据主键查找客户端信息
     *
     * @param id 主键
     * @return 结果
     */
    Client findByPrimaryKey(Long id);

    /**
     * 根据所提供的客户端信息修改未删除的客户端数据
     *
     * @param record 客户端信息
     * @return 结果
     */
    boolean update(Client record);

    /**
     * 根据所提供的客户端信息批量修改未删除的客户端数据
     *
     * @param list 客户端信息
     * @return 结果
     */
    boolean update(Collection<Client> list);

    /**
     * 根据所提供的客户端信息批量插入客户端数据
     *
     * @param list 客户端信息
     * @return 结果
     */
    boolean batchInsert(Collection<Client> list);
}
