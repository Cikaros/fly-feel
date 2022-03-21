package io.gitee.define.service;

import io.gitee.define.entity.Account;
import org.springframework.lang.NonNull;

import java.util.Collection;

/**
 * 账户基础操作业务
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public interface IAccountService {

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
     * 根据所提供的账户信息新增或修改数据
     *
     * @param record 账户信息
     * @return 结果
     */
    boolean insertOrUpdate(Account record);

    /**
     * 根据所提供的账户信息新增
     *
     * @param record 账户信息
     * @return 结果
     */
    boolean insert(Account record);

    /**
     * 根据主键查找账户信息
     *
     * @param id 主键
     * @return 结果
     */
    Account findByPrimaryKey(@NonNull Long id);

    /**
     * 根据所提供的账户信息修改未删除的账户数据
     *
     * @param record 账户信息
     * @return 结果
     */
    int update(Account record);

    /**
     * 根据所提供的账户信息批量修改未删除的账户数据
     *
     * @param list 账户信息
     * @return 结果
     */
    int update(Collection<Account> list);

    /**
     * 根据所提供的账户信息批量插入账户数据
     *
     * @param list 账户信息
     * @return 结果
     */
    int batchInsert(Collection<Account> list);

}