package io.gitee.core.controller;

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
public class BaseController {

    protected final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected I18nService i18n;

    @Autowired
    public void setI18n(I18nService i18n) {
        this.i18n = i18n;
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return String.format("redirect:%s", url);
    }

}

