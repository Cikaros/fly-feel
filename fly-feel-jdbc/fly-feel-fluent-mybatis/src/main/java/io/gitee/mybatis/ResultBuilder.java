package io.gitee.mybatis;

import cn.org.atool.fluent.mybatis.model.IPagedList;
import io.gitee.core.entity.model.ListData;
import io.gitee.core.entity.model.RestResult;

/**
 * 构造分页数据返回
 *
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
public class ResultBuilder {

    public static RestResult build(IPagedList<?> list) {
        return RestResult.success(new ListData(list.getData(), list.getTotal()));
    }

}
