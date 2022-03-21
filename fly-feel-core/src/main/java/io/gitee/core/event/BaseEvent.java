package io.gitee.core.event;

import io.gitee.core.enums.EventType;
import org.springframework.context.ApplicationEvent;

/**
 * Application事件对象
 *
 * @author Cikaros
 * @date 2021/7/17
 */
public class BaseEvent<T> extends ApplicationEvent {

    /**
     * 事件名称
     */
    private final String name;

    /**
     * 事件类型
     */
    private final EventType type;

    /**
     * 事件数据
     */
    private final T data;

    private BaseEvent(EventType type, T data) {
        super(new Object());
        this.name = "";
        this.type = type;
        this.data = data;
    }

    private BaseEvent(String name, EventType type, T data) {
        super(new Object());
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public EventType getType() {
        return type;
    }

    public T getData() {
        return data;
    }

    public static <T> BaseEvent<T> newInstance(String name, EventType type, T data) {
        return new BaseEvent<T>(name, type, data);
    }

    public static <T> BaseEvent<T> newInstance(EventType type, T data) {
        return new BaseEvent<T>(type, data);
    }
}
