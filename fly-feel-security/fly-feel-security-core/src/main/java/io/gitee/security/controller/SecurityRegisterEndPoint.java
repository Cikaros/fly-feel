package io.gitee.security.controller;

import io.gitee.core.controller.BaseController;
import io.gitee.core.entity.model.RestResult;
import io.gitee.define.service.ILogger;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Security注册接入点
 *
 * @author Cikaros
 * @date 2022/4/7
 */
@Controller
public class SecurityRegisterEndPoint extends BaseController implements ILogger {

    @RequestMapping(value = "/register", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RestResult registerToJson() {
        return RestResult.error();
    }

    @RequestMapping(value = "/register")
    public String registerToForm() {
        return redirect("");
    }

}
