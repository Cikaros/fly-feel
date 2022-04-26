# Service接口定义与实现

## 基础

Service的写法也与之前的写法大差不差。

```java
public interface IDemoService{
    
    String demo();
}

@Service
public class DemoServiceImpl extends BaseService implements IDemoService{

    @Override
    public String demo(){
        return "demo";
    }
    
}
```

## 进阶

### 日志打印

写入必要的日志，实现`io.gitee.define.service.ILogger`接口即可

```java

@Service
public class DemoServiceImpl extends BaseService implements IDemoService,ILogger{

    @Override
    public String demo(){
        log.info("demo");
        return "demo";
    }
    
}
```

## 常用系统业务类