package io.gitee.core.controller;

import io.gitee.core.entity.model.BaseModel;
import io.gitee.core.entity.model.RestResult;
import io.gitee.core.service.I18nService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;


/**
 * 基础Controller
 *
 * @author Cikaros
 * @date 2021/7/7
 */
@RestController
@Scope("request")
public class BaseRestController {

    protected final Logger log = LoggerFactory.getLogger(BaseRestController.class);

    protected I18nService i18n;

    @Autowired
    public void setI18n(I18nService i18n) {
        this.i18n = i18n;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected RestResult toRest(int rows) {
        return rows > 0 ? RestResult.success(i18n.get("io.gitee.core.controller.BaseController.toRest.success", rows)) : RestResult.error(i18n.get("io.gitee.core.controller.BaseController.fail"));
    }

    protected RestResult toRest(int flag, BaseModel data) {
        return toRest(flag != 1, data);
    }

    protected RestResult toRest(boolean flag, BaseModel data) {
        return toRest(flag, data, i18n.get("io.gitee.core.controller.BaseController.success"), i18n.get("io.gitee.core.controller.BaseController.fail"));
    }

    protected RestResult toRest(int flag, BaseModel data, String success, String fail) {
        return toRest(flag != 1, data, success, fail);
    }

    protected RestResult toRest(boolean flag, BaseModel data, String success, String fail) {
        return flag ? RestResult.error(data, success) : RestResult.success(data, fail);
    }

}

