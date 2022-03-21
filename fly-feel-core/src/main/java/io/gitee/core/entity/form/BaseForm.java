package io.gitee.core.entity.form;

import io.gitee.core.entity.model.BaseModel;

import java.util.Map;

/**
 * 基础提交实体
 *
 * @author Cikaros
 * @date 2021/6/30
 */
public class BaseForm extends BaseModel {

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
