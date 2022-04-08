package io.gitee.core.util;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义断言工具类
 *
 * @author Cikaros
 * @date 2022/4/8
 */
public abstract class Assert extends org.springframework.util.Assert {

    public static void state(boolean expression, RuntimeException exception) {
        if (!expression) {
            throw exception;
        }
    }

    public static void isTrue(boolean expression, RuntimeException exception) {
        if (!expression) {
            throw exception;
        }
    }


    public static void isNull(@Nullable Object object, RuntimeException exception) {
        if (object != null) {
            throw exception;
        }
    }

    public static void notNull(@Nullable Object object, RuntimeException exception) {
        if (object == null) {
            throw exception;
        }
    }


    public static void hasLength(@Nullable String text, RuntimeException exception) {
        if (!StringUtils.hasLength(text)) {
            throw exception;
        }
    }

    public static void hasText(@Nullable String text, RuntimeException exception) {
        if (!StringUtils.hasText(text)) {
            throw exception;
        }
    }

    public static void notEmpty(@Nullable Object[] array, RuntimeException exception) {
        if (ObjectUtils.isEmpty(array)) {
            throw exception;
        }
    }


    public static void noNullElements(@Nullable Object[] array, RuntimeException exception) {
        if (array != null) {
            int var3 = array.length;
            for (Object element : array) {
                if (element == null) {
                    throw exception;
                }
            }
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, RuntimeException exception) {
        if (CollectionUtils.isEmpty(collection)) {
            throw exception;
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, RuntimeException exception) {
        if (CollectionUtils.isEmpty(map)) {
            throw exception;
        }
    }

}
