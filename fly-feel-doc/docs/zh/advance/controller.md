# Controller接口定义与实现

## 基础

基础的实现方式跟我们平常使用 **SpringMvc** 的几乎一致：

```java

@Controller
public class CustomController extends BaseController {

    @GetMapping("/")
    public String ok() {
        //重定向的写法
        return redirect("index.html");
    }

    @PostMapping("/")
    public String ok() {
        //转发的写法
        return "index.html";
    }

}

@RestController
public class CustomRestController extends BaseRestController {
    @PostMapping("/ok")
    public RestResult ok() {
        return RestResult.success(null);
    }

    @PostMapping("/err")
    public RestResult err() {
        return RestResult.error(null);
    }

    @PostMapping("/list")
    public RestResult list() {
        IPage<?> list = getList();//假数据
        return RestResult.success(list);
    }
}
```

## 进阶

### 日志打印

写入必要的日志，实现`io.gitee.define.service.ILogger`接口即可

```java

@Controller
public class CustomController extends BaseController implements ILogger {

    @GetMapping("/")
    public String ok() {
        //重定向的写法
        log.debug("重定向的写法: redirect(\"index.html\");");
        return redirect("index.html");
    }

    @PostMapping("/")
    public String ok() {
        log.debug("转发的写法: \"index.html\"");
        return "index.html";
    }

}

// RestController 写法同上
```

### 接口定义

使用Feign可定义接口规范，之后实现对应的接口。确保一致性。

三方业务需要调用服务时，可使用`Feign`模块直接调用。实现如下：

```java

@FeignClient("${feign.paths.custom-service}")
public interface CustomFeignClient {

    @GetMapping("/")
    String ok();

    @PostMapping("/")
    String ok();

}

@Controller
public class CustomController extends BaseController implements CustomFeignClient, ILogger {

    public String ok() {
        //重定向的写法
        log.debug("重定向的写法: redirect(\"index.html\");");
        return redirect("index.html");
    }

    public String ok() {
        log.debug("转发的写法: \"index.html\"");
        return "index.html";
    }

}
```