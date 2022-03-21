package io.gitee.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础业务类
 *
 * @author Cikaros
 * @date 2021/7/8
 */
@Service
public class BaseService {

    protected final Logger log = LoggerFactory.getLogger(BaseService.class);

    protected I18nService i18n;

    @Autowired
    public void setI18n(I18nService i18n) {
        this.i18n = i18n;
    }

}
