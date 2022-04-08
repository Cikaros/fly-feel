package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

import java.util.Date;

/**
 * 缓存表
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public class Cache extends BaseModel {
    /**
     * 键
     */
    private String key;

    /**
     * 验证码
     */
    private String value;

    /**
     * 过期时长，默认永不过期
     */
    private Long expires;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}