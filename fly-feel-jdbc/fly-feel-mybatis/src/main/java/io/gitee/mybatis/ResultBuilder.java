package io.gitee.mybatis;

import com.github.pagehelper.PageInfo;
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

    public static RestResult build(PageInfo<?> list) {
        return RestResult.success(new ListData(list.getList(), list.getTotal()));
    }

}
