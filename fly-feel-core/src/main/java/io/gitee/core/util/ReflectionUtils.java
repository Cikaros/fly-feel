package io.gitee.core.util;

import java.lang.reflect.Field;

/**
 * 反射工具
 *
 * @author Cikaros
 * @date 2021/12/20
 */
public class ReflectionUtils {

    /**
     * 为对象的所有属性赋值为null
     *
     * @param o 对象
     * @throws IllegalAccessException 无法访问异常
     */
    public static void nullifyObjects(Object o) throws IllegalAccessException {
        for (Field f : o.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            f.set(o, null);
        }
        for (Field f : o.getClass().getSuperclass().getDeclaredFields()) {
            f.setAccessible(true);
            f.set(o, null);
        }
    }

    /**
     * 为对象的特定属性赋值为null
     *
     * @param o    对象
     * @param type 类型
     * @throws IllegalAccessException 无法访问异常
     */
    public static void nullifyObjectsByType(Object o, Class<?> type) throws IllegalAccessException {
        for (Field f : o.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getType().equals(type)) {
                f.set(o, null);
            }
        }
        for (Field f : o.getClass().getSuperclass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getType().equals(type)) {
                f.set(o, null);
            }
        }
    }

    /**
     * 为对象的空字符串属性赋值为null
     *
     * @param o 对象
     * @throws IllegalAccessException 无法访问异常
     */
    public static void nullifyStrings(Object o) throws IllegalAccessException {
        for (Field f : o.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getType().equals(String.class)) {
                String value = (String) f.get(o);
                if (value != null && value.trim().isEmpty()) {
                    f.set(o, null);
                }
            }
        }
        for (Field f : o.getClass().getSuperclass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getType().equals(String.class)) {
                String value = (String) f.get(o);
                if (value != null && value.trim().isEmpty()) {
                    f.set(o, null);
                }
            }
        }
    }
}
