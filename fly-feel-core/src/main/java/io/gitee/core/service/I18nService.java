package io.gitee.core.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class I18nService {

    private final MessageSource messageSource;

    public I18nService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String key) {
        return get(key, Locale.getDefault());
    }

    public String get(String key, Locale locale) {
        return messageSource.getMessage(key, null, locale);
    }

    public String get(String key, Object... args) {
        return get(key, Locale.getDefault(), args);
    }

    public String get(String key, Locale locale, Object... args) {
        return messageSource.getMessage(key, args, locale);
    }
}
