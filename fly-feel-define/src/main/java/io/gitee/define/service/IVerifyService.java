package io.gitee.define.service;

/**
 * 验证码业务
 *
 * @author Cikaros
 * @date 2022/3/23
 * @since v1.0
 */
public interface IVerifyService {

    /**
     * 获取一个验证码对象
     */
    void getInstance();

    /**
     * 是否匹配
     *
     * @param code 验证码
     * @return 结果
     */
    boolean isMatches(String code);
}
