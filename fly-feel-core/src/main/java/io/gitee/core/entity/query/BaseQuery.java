package io.gitee.core.entity.query;


import io.gitee.core.entity.query.verify.PageGroup;
import io.gitee.core.entity.model.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * 基础查询实体
 *
 * @author Cikaros
 * @date 2021/6/30
 */
public class BaseQuery extends BaseModel {

    /**
     * 当前页数
     */
    @NotNull(groups = {PageGroup.class}, message = "page参数未准确接收，请检查是否按照要求传递该参数！")
    @Min(groups = {PageGroup.class}, message = "page参数不能小于1！", value = 1)
    protected Integer page;

    /**
     * 每页显示数量
     */
    @NotNull(groups = {PageGroup.class}, message = "limit参数未准确接收，请检查是否按照要求传递该参数！")
    @Min(groups = {PageGroup.class}, message = "limit参数不能小于1！", value = 1)
    @Max(groups = {PageGroup.class}, message = "limit参数不能大于100！", value = 100)
    protected Integer limit;

    /**
     * 起始时间
     */
    protected Date startTime;

    /**
     * 结束时间
     */
    protected Date endTime;

    public BaseQuery() {
    }

    public BaseQuery(Integer page, Integer limit, Date startTime, Date endTime) {
        this.page = page;
        this.limit = limit;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseQuery query = (BaseQuery) o;
        return Objects.equals(page, query.page) && Objects.equals(limit, query.limit) && Objects.equals(startTime, query.startTime) && Objects.equals(endTime, query.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), page, limit, startTime, endTime);
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                super.toString() +
                ",page=" + page +
                ", limit=" + limit +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
