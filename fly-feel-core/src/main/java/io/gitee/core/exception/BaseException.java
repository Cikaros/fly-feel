package io.gitee.core.exception;


import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 统一自定义异常类
 *
 * @author Cikaros
 * @date 2021/7/12
 */
public class BaseException extends RuntimeException {

    protected static ResourceBundle i18n = ResourceBundle.getBundle("i18n/Exception");

    public BaseException(String key, Object... args) {
        super(MessageFormat.format(i18n.getString(key), args));
    }

    public BaseException(String key, Throwable throwable, Object... args) {
        super(MessageFormat.format(i18n.getString(key), args), throwable);
    }

    public BaseException(String message) {
        this("io.gitee.core.exception.BaseException", message);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(Throwable throwable, Object... args) {
        this("io.gitee.core.exception.BaseException", throwable, args);
    }

}
