package io.gitee.define.service;

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
     * @param key
     * @param value
     */
    void cache(String key, Object value);

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
