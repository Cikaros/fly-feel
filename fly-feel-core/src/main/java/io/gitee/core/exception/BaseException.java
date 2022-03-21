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

    public BaseException(String message) {
        super(MessageFormat.format(i18n.getString("io.gitee.core.exception.BaseException"), message));
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String message, Throwable throwable) {
        super(MessageFormat.format(i18n.getString("io.gitee.core.exception.BaseException"), message), throwable);
    }

}
