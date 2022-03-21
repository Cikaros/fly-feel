package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 组件资源表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class Component extends BaseModel {
    /**
     * 组件名称
     */
    private String name;

    /**
     * 组件描述
     */
    private String description;

    /**
     * 父组件id
     */
    private Long parentId;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 类型
     */
    private Byte componentType;

    /**
     * 组件地址
     */
    private String componentPath;

    /**
     * 显示状态
     */
    private Byte visible;

    /**
     * 图标
     */
    private String icon;

    /**
     * 同级优先级
     */
    private Byte order;


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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Byte getComponentType() {
        return componentType;
    }

    public void setComponentType(Byte componentType) {
        this.componentType = componentType;
    }

    public String getComponentPath() {
        return componentPath;
    }

    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }

    public Byte getVisible() {
        return visible;
    }

    public void setVisible(Byte visible) {
        this.visible = visible;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getOrder() {
        return order;
    }

    public void setOrder(Byte order) {
        this.order = order;
    }

}