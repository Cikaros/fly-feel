package io.gitee.core.entity.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 统一返回结果集
 *
 * @author Cikaros
 * @date 2021/6/30
 */
public class RestResult extends ResponseEntity<Data> {

    protected RestResult() {
        super(new Data(), HttpStatus.NO_CONTENT);
    }

    protected RestResult(HttpStatus status) {
        super(new Data(status), status);
    }

    protected RestResult(HttpStatus status, Object data, String message) {
        super(new Data(status, data, message), status);
    }

    protected RestResult(HttpStatus status, Object data) {
        super(new Data(status, data), status);
    }

    protected RestResult(HttpStatus status, String message) {
        super(new Data(status, message), status);
    }

    protected RestResult(Object data, String message) {
        super(new Data(data, message), HttpStatus.OK);
    }

    protected RestResult(Object data) {
        super(new Data(data), HttpStatus.OK);
    }

    protected RestResult(String message) {
        super(new Data(message), HttpStatus.OK);
    }

    public String getMessage() {
        return Objects.requireNonNull(super.getBody()).getMessage();
    }

    /**
     * 构造统一返回结果集
     *
     * @param status HTTP状态
     * @return {@link RestResult}
     */
    public static RestResult newInstance(HttpStatus status) {
        return newInstance(status, "");
    }

    /**
     * 构造统一返回结果集
     *
     * @param status  HTTP状态
     * @param message 显示信息
     * @return {@link RestResult}
     */
    public static RestResult newInstance(HttpStatus status, String message) {
        return newInstance(status, null, message);
    }

    /**
     * 构造统一返回结果集
     *
     * @param status HTTP状态
     * @param data   数据
     * @return {@link RestResult}
     */
    public static RestResult newInstance(HttpStatus status, Object data) {
        return new RestResult(status, data);
    }

    /**
     * 构造统一返回结果集
     *
     * @param status  HTTP状态
     * @param data    数据
     * @param message 显示信息
     * @return {@link RestResult}
     */
    public static RestResult newInstance(HttpStatus status, Object data, String message) {
        return new RestResult(status, data, message);
    }

    /**
     * 构造成功结果集
     *
     * @param data    数据
     * @param message 显示信息
     * @return {@link RestResult}
     */
    public static RestResult success(BaseModel data, String message) {
        return newInstance(HttpStatus.OK, data, message);
    }

    /**
     * 构造成功结果集
     *
     * @param message 显示信息
     * @return {@link RestResult}
     */
    public static RestResult success(String message) {
        return newInstance(HttpStatus.OK, null, message);
    }

    /**
     * 构造成功结果集
     *
     * @param data 数据
     * @return {@link RestResult}
     */
    public static RestResult success(BaseModel data) {
        return newInstance(HttpStatus.OK, data.clone(BaseModel.Field.ID));
    }

    /**
     * 构造成功结果集
     *
     * @param data 数据
     * @return {@link RestResult}
     */
    public static RestResult success(Object data) {
        return newInstance(HttpStatus.OK, data);
    }

    /**
     * 构造成功结果集
     *
     * @param list List数据
     * @return {@link RestResult}
     */
    public static RestResult success(List<BaseModel> list) {
        return newInstance(HttpStatus.OK, list.stream().map(data -> data.clone(BaseModel.Field.ID)).collect(Collectors.toList()));
    }

    /**
     * 构造成功结果集
     *
     * @param list  List数据
     * @param total 总数量
     * @return {@link RestResult}
     */
    public static RestResult success(List<?> list, long total) {
        return newInstance(HttpStatus.OK, new ListData(list, total));
    }

    /**
     * 构造成功结果集
     *
     * @return {@link RestResult}
     */
    public static RestResult success() {
        return newInstance(HttpStatus.NO_CONTENT);
    }


    /**
     * 构造成功结果集
     *
     * @return {@link RestResult}
     */
    public static RestResult success(Data data) {
        return newInstance(HttpStatus.OK, data);
    }

    /**
     * 构造失败结果集
     *
     * @param data    数据
     * @param message 显示信息
     * @return {@link RestResult}
     */
    public static RestResult error(BaseModel data, String message) {
        return newInstance(HttpStatus.INTERNAL_SERVER_ERROR, data, message);
    }

    /**
     * 构造失败结果集
     *
     * @param message 显示信息
     * @return {@link RestResult}
     */
    public static RestResult error(String message) {
        return newInstance(HttpStatus.INTERNAL_SERVER_ERROR, null, message);
    }

    /**
     * 构造失败结果集
     *
     * @param data 数据
     * @return {@link RestResult}
     */
    public static RestResult error(BaseModel data) {
        return newInstance(HttpStatus.INTERNAL_SERVER_ERROR, data.clone(BaseModel.Field.ID));
    }

    /**
     * 构造失败结果集
     *
     * @return {@link RestResult}
     */
    public static RestResult error() {
        return newInstance(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 构造失败结果集
     *
     * @return {@link RestResult}
     */
    public static RestResult error(Data data) {
        return newInstance(HttpStatus.INTERNAL_SERVER_ERROR, data);
    }

    /**
     * 构造成功结果集
     *
     * @param list List数据
     * @return {@link RestResult}
     */
    public static RestResult error(List<BaseModel> list) {
        return newInstance(HttpStatus.INTERNAL_SERVER_ERROR, list.stream().map(data -> data.clone(BaseModel.Field.ID)).collect(Collectors.toList()));
    }

    /**
     * 构造空列表
     *
     * @return {@link RestResult}
     */
    public static RestResult emptyListResult() {
        return new RestResult(HttpStatus.GONE, ListData.empty());
    }

    /**
     * 构造Map类型返回数据
     *
     * @return {@link Map}
     */
    public Map<String, Object> of() {
        Map<String, Object> data = new HashMap<>();
        if (Objects.nonNull(this.getBody())) {
            data.put("code", this.getStatusCodeValue());
            data.put("data", this.getBody().getData());
            data.put("message", this.getBody().getMessage());
            if (this.getBody() instanceof ListData) {
                data.put("count", ((ListData) this.getBody()).getCount());
            }
        }
        return data;
    }

}
