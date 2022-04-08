package io.gitee.define.service;

import io.gitee.define.entity.Account;
import io.gitee.define.entity.SecurityAccount;
import org.springframework.lang.NonNull;

/**
 * Security 账户业务
 *
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
public interface IAccountSecurityService extends IAccountService {

    /**
     * 根据用户名查找未删除的账户信息
     *
     * @param username 用户名
     * @return 结果
     */
    SecurityAccount findByUsername(@NonNull String username);

    /**
     * 根据用户名禁用用户
     *
     * @param username 用户名
     * @return 结果
     */
    boolean disableByUsername(@NonNull String username);

    /**
     * 根据用户名解除禁用
     *
     * @param username 用户名
     * @return 结果
     */
    boolean enableByUsername(@NonNull String username);

    /**
     * 注册新用户
     *
     * @param account 用户信息
     * @return 成功的用户信息
     */
    Account registered(@NonNull Account account);

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @return 结果
     */
    boolean isExistByUsername(@NonNull String username);



}
