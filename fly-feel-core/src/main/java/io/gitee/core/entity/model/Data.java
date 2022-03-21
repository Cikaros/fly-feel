package io.gitee.core.entity.model;

import org.springframework.http.HttpStatus;

/**
 * @author Cikaros
 * @date 2022/3/18
 * @since v1.0
 */
class Data {

    /**
     * HTTP状态
     */
    private final HttpStatus status;

    /**
     * 结果编码
     */
    private final Integer code;

    /**
     * 数据集
     */
    private final Object data;

    /**
     * 返回信息
     */
    private final String message;

    protected Data() {
        this.status = HttpStatus.NO_CONTENT;
        this.code = this.status.value();
        this.data = null;
        this.message = this.status.getReasonPhrase();
    }

    protected Data(HttpStatus status) {
        this.status = status;
        this.code = this.status.value();
        this.data = null;
        this.message = this.status.getReasonPhrase();
    }

    protected Data(HttpStatus status, Object data, String message) {
        this.status = status;
        this.code = this.status.value();
        this.data = data;
        this.message = message;
    }

    protected Data(HttpStatus status, Object data) {
        this.status = status;
        this.code = this.status.value();
        this.data = data;
        this.message = status.getReasonPhrase();
    }

    protected Data(Object data, String message) {
        this.status = HttpStatus.OK;
        this.code = this.status.value();
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}
