package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 角色表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class Role extends BaseModel {
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 是否为初始化角色
     */
    private Boolean initialize;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInitialize() {
        return initialize;
    }

    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }
}