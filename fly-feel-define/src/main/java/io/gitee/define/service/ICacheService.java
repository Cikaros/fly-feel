package io.gitee.define.service;

import java.util.concurrent.TimeUnit;

/**
 * 数据缓存业务接口
 *
 * @author Cikaros
 * @date 2022/4/2
 * @since v1.0
 */
public interface ICacheService {

    /**
     * 缓存键值对
     *
     * @param key   键
     * @param value 值
     */
    void cache(String key, Object value);

    /**
     * 缓存键值对
     *
     * @param key     键
     * @param value   值
     * @param expires 过期时间（ms）
     */
    void cache(String key, Object value, long expires);

    /**
     * 缓存键值对
     *
     * @param key     键
     * @param value   值
     * @param expires 过期时间
     * @param unit    时间单位
     */
    void cache(String key, Object value, long expires, TimeUnit unit);

    /**
     * 是否已过期
     *
     * @param key 键
     * @return 结果
     */
    boolean isExpires(String key);

    /**
     * 获取缓存数据
     *
     * @param key 键
     */
    <T> T get(String key);

    /**
     * 获取缓存数据
     *
     * @param key   键
     * @param clazz 数据类型
     */
    <T> T get(String key, Class<T> clazz);

}
