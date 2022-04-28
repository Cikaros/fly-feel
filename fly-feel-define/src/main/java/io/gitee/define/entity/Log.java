package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;
import io.gitee.core.enums.EventType;

import java.util.Map;

/**
 * 日志表
 *
 * @author Cikaros
 * @date 2022/4/7
 */
public class Log extends BaseModel {
    /**
     * 日志类型
     */
    private EventType type;

    /**
     * 日志信息
     */
    private String msg;

    /**
     * 日志触发者,默认为系统触发
     */
    private Long triggers;

    /**
     * 触发地址
     */
    private String location;

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTriggers() {
        return triggers;
    }

    public void setTriggers(Long triggers) {
        this.triggers = triggers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}