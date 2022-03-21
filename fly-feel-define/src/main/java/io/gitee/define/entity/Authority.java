package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 权限表
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class Authority extends BaseModel {
    /**
    * 权限名称
    */
    private String name;

    /**
    * 权限编码
    */
    private String code;

    /**
    * 权限描述
    */
    private String description;


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

}