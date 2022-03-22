package io.gitee.define.service;

import io.gitee.define.entity.SecurityAccount;
import org.springframework.lang.NonNull;

/**
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
}
