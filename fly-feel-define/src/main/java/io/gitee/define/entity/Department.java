package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 部门表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class Department extends BaseModel {
    /**
     * 父部门ID
     */
    private Long parentBy;

    /**
     * 顶级父部门Id
     */
    private Long topId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 组织描述
     */
    private String description;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Byte order;


    public Long getParentBy() {
        return parentBy;
    }

    public void setParentBy(Long parentBy) {
        this.parentBy = parentBy;
    }

    public Long getTopId() {
        return topId;
    }

    public void setTopId(Long topId) {
        this.topId = topId;
    }

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