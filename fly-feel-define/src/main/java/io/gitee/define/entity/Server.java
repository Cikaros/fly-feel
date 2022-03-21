package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 资源服务器
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class Server extends BaseModel {
    /**
     * 资源服务器名称
     */
    private String name;

    /**
     * 资源服务器描述
     */
    private String description;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 资源服务器地址
     */
    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}