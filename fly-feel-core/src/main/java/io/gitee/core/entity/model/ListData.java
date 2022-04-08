package io.gitee.core.entity.model;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

/**
 * 统一分页数据返回结果集
 *
 * @author Cikaros
 * @date 2021/12/20
 */
class ListData extends Data {
    /**
     * 当前记录数
     */
    private final long count;

    /**
     * 列表数据
     */
    private final List<?> data;

    /**
     * 表格数据对象
     */
    private ListData() {
        super(HttpStatus.GONE);
        count = 0;
        data = Collections.emptyList();
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public ListData(List<?> list, long total) {
        super(HttpStatus.OK);
        this.data = list;
        this.count = total;
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    private ListData(HttpStatus status, List<?> list, long total) {
        super(status);
        this.data = list;
        this.count = total;
    }

    public long getCount() {
        return count;
    }

    public List<?> getData() {
        return data;
    }

    static Data empty() {
        return new ListData();
    }

}
