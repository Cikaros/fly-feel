package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 代码项
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class CodeItem extends BaseModel {
    /**
     * 代码项标题
     */
    private String title;

    /**
     * 组织描述
     */
    private String description;

    /**
     * 分类标识
     */
    private String type;

    /**
     * 代码项值
     */
    private String value;

    /**
     * 排序
     */
    private Byte order;

    /**
     * 版本号
     */
    private Long version;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Byte getOrder() {
        return order;
    }

    public void setOrder(Byte order) {
        this.order = order;
    }

}