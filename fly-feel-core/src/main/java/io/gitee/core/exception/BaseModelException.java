package io.gitee.core.exception;

import io.gitee.core.entity.model.BaseModel;

/**
 * @author Cikaros
 * @date 2022/4/8
 */
public class BaseModelException extends BaseException {

    private final BaseModel model;

    public BaseModelException(BaseModel model, Object... args) {
        super("io.gitee.core.exception.BaseModelException", args);
        this.model = model;
    }

    public BaseModel getModel() {
        return model;
    }
}
