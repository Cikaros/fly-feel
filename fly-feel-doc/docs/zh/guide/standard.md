## 为什么要制定规范

制定规范的初衷也是为了解决一下问题：

1. 团队内部协作
2. 可追溯的更新版本
3. 组件化设计，可复用性强
4. 通过组件化设计，提升可复用性，来提升开发效率
5. 如果是多个业务线并行，统一的规范能够起到统一的作用

简单来说就是为了统一任务目标的解决方式和手段，便于团队合作完成同一个任务或同一类任务。

## 命名规范

### 项目名称

项目模块名称统一按照`project-xxxx-xxxx`的格式命名。

公共模块 `common-xxxx-xxxx`、业务模块 `service-xxxx-xxxx`等。

### <a id='package'>包路径</a>

> 这里请严格遵照规范书写！便于阅读和维护。
>
> 重复业务请抽象为抽象类或接口等形式。做到开闭原则和单一原则。

```text
|com.shibo.xxxx 项目
|-- core 核心基础
	|-- enums 公共的枚举类实现
	|-- entity 公共的实体类 基础的实体实现
        |-- form 表单类 常用于表单提交的数据
        |-- query 查询类 常用的查询提交的数据
            |-- verify 自定义的查询校验分组
        |-- model 数据包类 常用于返回给前端的数据
	|-- mapper 公共的单表查询mapper实现
    |-- filter 公共的Web过滤器的实现 内部可自行分类
    |-- interceptor 公共的SpringMVC拦截器的实现 内部可自行分类
	|-- utils 自定义的工具类
	|-- feign 公共的FeignClient实现
|-- common 公共业务模块
	|-- controller 控制层
    |-- entity 仅本模块所需实体类
        |-- form 表单类 常用于表单提交的数据
        |-- query 查询类 常用的查询提交的数据
            |-- verify 自定义的查询校验分组
        |-- model 数据包类 常用于返回给前端的数据
    |-- exception 仅本模块所需自定义异常
        |--handler 自定义异常的处理
    |-- service 业务层 一般放接口
        |-- 业务的具体实现
    |-- mapper 仅本模块所需多表联查的Mapper实现
    |-- feign 仅本模块所需FeignClient实现
|-- xxxxx1 子业务模块1
	|-- controller 控制层
	|-- enums 仅本模块所需枚举类实现
    |-- entity 仅本模块所需实体类
        |-- form 表单类 常用于表单提交的数据
        |-- query 查询类 常用的查询提交的数据
            |-- verify 自定义的查询校验分组
        |-- model 数据包类 常用于返回给前端的数据
    |-- exception 仅本模块所需自定义异常
        |--handler 自定义异常的处理
    |-- service 业务层 一般放接口
        |-- 业务的具体实现
    |-- filter 仅本模块所需Web过滤器的实现 内部可自行分类
    |-- interceptor 仅本模块所需SpringMVC拦截器的实现 内部可自行分类
    |-- mapper 仅本模块所需多表联查的Mapper实现
    |-- feign 仅本模块所需FeignClient实现
|-- xxxxx2 子业务模块2 （同子业务模块1）
```

## 开发规范

### 环境规范

- 开发环境统一使用IDEA或Eclipse等环境(根据团队情况来决定)
- JDK版本统一
- maven的版本与编码统一
- 编码环境需统一使用`utf-8`

![编码配置](/images/Snipaste_2021-09-06_10-11-18.jpg)

### 注释规范

#### 类注释

```java
/**
 * ${DESCRIPTION}
 *
 * @author 作者名称
 * @date ${DATE}
 */
```

- DESCRIPTION 类表述
- DATE 创建时间

#### 方法注释

```java
/**
 * 方法描述
 *
 * @param param1 参数1描述
 * @param param2 参数1描述
 * @return 返回值描述
 * @since 版本
 */
```

#### 常量注释

```java
/**
 * 常量描述
 */
```

一般可直接根据名称看出常量意义的常量可不写注释。

### 接口规范

#### 统一数据返回

```json
{
    "code": 200,
    "data": {},
    "message": "",
    "count": 0
}
```

| 字段    | 类型         | 说明                              |
| ------- | ------------ | --------------------------------- |
| code    | Number       | Http状态码                        |
| data    | Object/Array | 数据集，分页时Array，一般为Object |
| message | String       | 返回信息                          |
| count   | Number       | 记录总条数，分页时可用            |

上述格式对应了两个返回数据类：`RestResult`和`ListData`。`RestResult`用于一般数据返回。`ListData`则用于分页数据返回。

常见方式如下:

```java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {
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

#### 遵循原则

- 已经写好的模块，不允许再修改。需要扩充功能，请继承实现新的类。

  <font color="red">注：</font>这里可参照spring-boot-admin模块下的`com.shibo.admin.controller.AdminComponentResourceController`
  的实现方法。

- 遇到错误统一抛异常，将错误交由统一异常处理类去处理。

#### 注入方式

统一注入方式为`@Resource`。<font color="red">禁止使用`@Autowired`。</font>

#### 统一Controller

```java

@Validated
public class BaseController {

    protected final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected RestResult toRest(int rows) {
        return rows > 0 ? RestResult.success(null) : RestResult.error(null);
    }

    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }
}
```

自定义Controller需继承`BaseController`

内置log日志输出。调试信息用`log.debug()`、必要的打印信息用`log.info()`，请不要删除debug是留下的日志语句，该日志可在配置文件中设置打印类型来关闭debug信息。

#### 统一Service

```java
public class BaseService {

    protected final Logger log = LoggerFactory.getLogger(BaseService.class);

    @Resource
    protected MessageUtils i18n;
}
```

自定义Service实现类需集成`BaseService`

内置log日志输出，同上。

内置国际化支持。可使用`i18n.message()`方法，获取`resources/i18n`中的国际化配置文件。

#### 统一实体

自定义Form实体统一继成`BaseForm`

自定义Query实体统一继承`BaseQuery`

需要在已有的实体上扩充属性，均使用继承的形式去书写

#### 统一业务Exception

```java
public class CustomException extends BaseException {

    protected static final MessageUtils i18n = SpringUtils.getBean(MessageUtils.class);

    public CustomException(String defaultMessage, Exception e) {
        super("spring-boot-project", null, null, defaultMessage, e);
    }

    public CustomException(String defaultMessage) {
        super(defaultMessage);
    }
}
```

自定义的异常统一继承`CustomException`。

需要处理自己的自定义异常类，请自行构建异常处理类。包目录规范请查看[包定义规范](#package)

内置国际化支持，同上。

#### 统一Feign

```java

@FeignClient("http://localhost:8080/system/sqlLog")
public interface CustomClient {

    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ListData list(@SpringQueryMap BaseQuery query);

}
```

feign client底层使用了`OpenFeign`。为和Springboot贴合，降低学习成本，本框架提供了`SpringMvcContract`来解决识别SpringMVC注解的问题。

定义好了feign client，需使用`@FeignClientScan`去扫描。

```java

@Configuration
@FeignClientScan({"com.shibo.xxxx.fegin"})
public class FeignClientConfig {
}
```

#### 统一枚举BaseEnum

我们在实现枚举时要注意以下几点：

- 自定义枚举必须为`enum`类
- 需要实现别名或着别值时请实现`com.shibo.framework.enums.core.EnumAliasName`或`com.shibo.framework.enums.core.EnumAliasValue`
- 若别名和别值均要实现则可直接实现`com.shibo.framework.enums.core.EnumAlias`即可

```java
public enum HttpStatus implements EnumAlias {

    /**
     * 继续。客户端应继续其请求
     */
    CONTINUE("CONTINUE", 100),

    /**
     * 切换协议。服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议
     */
    SWITCHING_PROTOCOLS("SWITCHING_PROTOCOLS", 101);

    private final String aliasName;

    private final int aliasValue;

    HttpStatus(String aliasName, int aliasValue) {
        this.aliasName = aliasName;
        this.aliasValue = aliasValue;
    }

    @Override
    public String aliasName() {
        return aliasName;
    }

    @Override
    public int aliasValue() {
        return aliasValue;
    }

}
```

- 定义好了枚举类，需使用`@EnumComponentScan`去扫描。

```java

@Configuration
@EnumComponentScan({"com.shibo.project.enums"})
public class CustomConfig {

}
```

#### 统一Mapper

Mapper实现方式同Mybatis plus。更多详情请查看[Mybatis](https://mybatis.org/mybatis-3/zh/index.html)和[Mybtais
Plus](https://mybatis.plus/)官网。

<font color="blue">这里需要注意的是：</font>**不要使用注解形式开发！**

<font color="green">注解开发虽然快速高效，但后期优化和阅读源码都很困难。所以请务必使用Mybatis提供的XML形式开发。</font>

<font color="red">禁止使用Mybatis Plus提供的`QueryWrapper`，这种实现方式使得代码臃肿、难以阅读且不便维护。</font>

<font color="red">注：</font>在Service中引入Mapper类时若此时的Mapper只存在一个实例则直接使用`@Resource`注入即可。若该Mapper还存在子接口，则必须使用`@Qualifier`
指定Mapper的名称。否则会出现如下异常：

```tex
Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.shibo.framework.resource.mapper.ComponentResourceTreeMapper' available: expected single matching bean but found 2: componentResourceTreeMapper,adminComponentResourceMapper
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveNotUnique(DependencyDescriptor.java:220)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1285)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1227)
	at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.autowireResource(CommonAnnotationBeanPostProcessor.java:521)
	at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.getResource(CommonAnnotationBeanPostProcessor.java:497)
	at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor$ResourceElement.getResourceToInject(CommonAnnotationBeanPostProcessor.java:650)
	at org.springframework.beans.factory.annotation.InjectionMetadata$InjectedElement.inject(InjectionMetadata.java:228)
	at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:119)
	at org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.postProcessProperties(CommonAnnotationBeanPostProcessor.java:318)
	... 38 common frames omitted
```

## 日志记录

本框架实现了两种日志记录机制。 第一种是使用注解`@Log`实现的日志记录。使用该注解会将日志记录至操作日志表中。 另一种是通过检测SQL的运行是否满足记录条件，再判断是否要记录。它会直接记录进SQL日志表中。

### @Log

- value/message 日志信息记录
- remark 备注
- result 记录接口返回数据
- args 记录接口参数数据

### SQL记录

SQL日志的记录，只需要在配置文件中配置即可。需要排除的Mapper请在`mybatis-plus.listener.ignore`中排除。

```yaml
mybatis-plus:
    global-config:
        banner: false
    configuration:
        cache-enabled: true
        use-generated-keys: true
        default-executor-type: reuse
        log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
    mapper-locations: classpath*:mapper/**/*Mapper.xml
    listener:
        # 监听类型 INSERT,UPDATE,DELETE,SELECT
        # 其他的SQL关键字均可作为监听类型，例如CREATE,ALTER,DROP等
        type:
            - INSERT
            - UPDATE
            - DELETE
        # 排除Mapper
        ignore:
            - UserDetailsMapper.xml #请不要删除该排除项
            - SqlLogMapper.xml #请不要删除该排除项
            - OperateLogMapper.xml #请不要删除该排除项
```

> <font color='red'>注意：请不要删除上述排除项，否则会陷入循环日志记录，直到溢出。</font>

## OpenFeign 注解说明

### @FeignClientScan

- basePackages/value 设置扫描包路径

### @FeignClient

在feign接口类上注解

- url/value 请求地址
- type feign客户端类型

### @SpringQueryMap

在feign接口方法参数实体上注解，适用于form传参

### @RequestMapping

在feign接口方法上注解，适用于form、json传参

### @GetMapping / @PostMapping

同上，只是细化了请求方式

### @RequestParam

在feign接口方法参数上注解，适用于form传参

### @RequestBody

在feign接口方法参数实体上注解，适用于json传参

## Minio

详细接口说明请查看[FileDao](file:///target/javadoc/com/shibo/framework/file/dao/FileDao.html)

### MinIO服务器 Object 的存储机制制定计划

```text
-- public 公共桶 用于存放一些公用数据
  |-- 内部按照 年/月/日/uuid 的格式存放数据

-- backup 备份桶 用于将来存放备份数据
  |-- 内部按照 备份类型/年/ 月+日+时分秒+uuid 的格式存放数据

-- users 用户数据 用于存放用户的私有数据
  |-- 内部按照 用户id/年/月/日/uuid 的格式存放数据

-- department 部门数据 用于存放部门内的公有数据
  |-- 内部按照 部门id/子部门id.../年/月/日/uuid 的格式存放数据
```

## 多数据源

多数据源使用了Mybatis-Plus官方提供的支持。文档地址:[https://baomidou.com/guide/dynamic-datasource.html](https://baomidou.com/guide/dynamic-datasource.html)

## 自定义工具库

在需要扫描的工具类中，加上`@Utils`即可，设置了工具类需要在配置类中加上注解`@UtilsScan`扫描工具对应的包路径

## Redis

详细接口说明请查看[RedisCache](file://target/javadoc/com/shibo/framework/redis/RedisCache.html)

## EasyExcel

详情请查看[EasyExcel文档](https://www.yuque.com/easyexcel/doc/easyexcel)

## 定时任务

详情请查看[Task Scheduling](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#scheduling-annotation-support)

## 数据校验

详情请查看[Va lidation1.1](https://beanvalidation.org/1.1/spec/#constraintdeclarationvalidationprocess-requirements-object)

|                   验证注解                   |                        验证的数据类型                        |                             说明                             |
| :------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|                 @AssertFalse                 |                       Boolean,boolean                        |                   验证注解的元素值是false                    |
|                 @AssertTrue                  |                       Boolean,boolean                        |                    验证注解的元素值是true                    |
|                   @NotNull                   |                           任意类型                           |                   验证注解的元素值不是null                   |
|                    @Null                     |                           任意类型                           |                    验证注解的元素值是null                    |
|                @Min(value=值)                | BigDecimal，BigInteger, byte,short, int, long，等任何Number或CharSequence（存储的是数字）子类型 |          验证注解的元素值大于等于@Min指定的value值           |
|               @Max(value=值)               |                        和@Min要求一样                        |          验证注解的元素值小于等于@Max指定的value值           |
|            @DecimalMin(value=值)             |                        和@Min要求一样                        |      验证注解的元素值大于等于@ DecimalMin指定的value值       |
|            @DecimalMax(value=值)             |                        和@Min要求一样                        |      验证注解的元素值小于等于@ DecimalMax指定的value值       |
| @Digits(integer=整数位数, fraction=小数位数) |                        和@Min要求一样                        |           验证注解的元素值的整数位数和小数位数上限           |
|          @Size(min=下限, max=上限)           |               字符串、Collection、Map、数组等                | 验证注解的元素值的在min和max（包含）指定区间之内，如字符长度、集合大小 |
|                    @Past                     |  java.util.Date,java.util.Calendar;Joda Time类库的日期类型   |           验证注解的元素值（日期类型）比当前时间早           |
|                   @Future                    |                       与@Past要求一样                        |           验证注解的元素值（日期类型）比当前时间晚           |
|                  @NotBlank                   |                      CharSequence子类型                      | 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的首位空格 |
|         @Length(min=下限, max=上限)          |                      CharSequence子类型                      |             验证注解的元素值长度在min和max区间内             |
|                  @NotEmpty                   |          CharSequence子类型、Collection、Map、数组           | 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0） |
|        @Range(min=最小值, max=最大值)        | BigDecimal,BigInteger,CharSequence, byte, short, int, long等原子类型和包装类型 |             验证注解的元素值在最小值和最大值之间             |
|  @Email(regexp=正则表达式,flag=标志的模式)   |                CharSequence子类型（如String）                | 验证注解的元素值是Email，也可以通过regexp和flag指定自定义的email格式 |
| @Pattern(regexp=正则表达式,flag=标志的模式)  |               String，任何CharSequence的子类型               |            验证注解的元素值与指定的正则表达式匹配            |
|                    @Valid                    |                        任何非原子类型                        | 指定递归验证关联的对象如用户对象中有个地址对象属性，如果想在验证用户对象时一起验证地址对象的话，在地址对象上加@Valid注解即可级联验证 |

中文说明在这儿[Springboot-validator的使用](https://cikaros.gitee.io/doc/c80621.html)

## 配置文件说明

```yaml
--- application.yml
spring:
    application:
        name: website-server #项目名称
    profiles:
        active: dev #选择配置版本
    resources:
        static-locations: classpath:/static/,classpath:/public/ #开启静态资源地址
        add-mappings: false
    mvc:
        throw-exception-if-no-handler-found: true #抛出404异常进行处理
    security:
        oauth2:
            resourceserver: #权限服务器配置
                opaquetoken:
                    resource-id: resourceId
                    client-id: 931e0bbe20a049bdb2e163f7b810436f
                    client-secret: aaUIVlEFQMnJgkgOMhPY0RFMaol0pj11
                    introspection-uri: http://wtf.shibo.com:8080/oauth/check_token
    jackson: #jackson配置
        date-format: yyyy-MM-dd hh:mm:ss
        default-property-inclusion: NON_NULL
# Mybatis Plus配置
mybatis-plus:
    global-config:
        banner: false
    configuration:
        cache-enabled: true
        use-generated-keys: true
        default-executor-type: reuse
        log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
    mapper-locations: classpath*:mapper/**/*Mapper.xml
    listener:
        # 监听类型 INSERT,UPDATE,DELETE,SELECT
        type:
            - INSERT
            - UPDATE
            - DELETE
        # 排除Mapper
        ignore:
            - UserDetailsMapper.xml
            - SqlLogMapper.xml
            - OperateLogMapper.xml
--- dev.yml
logging: #日志打印配置
    level:
        com.shibo: debug
        com.zfbxx: debug
management: #监控中心配置，在Debug时使用
    server:
        port: 8083

server:
    port: 8080
    servlet: #重复提交检查时长
        long-time: 0
    # 防止XSS攻击
    xss:
        # 过滤开关
        enabled: true
        # 排除链接（多个用逗号分隔）
        excludes: /system/notice/*
        # 匹配链接
        urlPatterns: /system/*,/monitor/*,/tool/*
spring:
    http:
        encoding:
            charset: UTF-8
    devtools:
        restart:
            enabled: true
    servlet: #文件上传与请求体大小限制
        multipart:
            max-file-size: 5MB
            max-request-size: 5MB
    data: #数据库
        mongodb:
            uri: mongodb://10.1.1.100:27017/yapi
            username:
            password:
    datasource: #数据库
        druid:
            stat-view-servlet:
                enabled: true
                loginUsername: admin
                loginPassword: 123456
        dynamic:
            primary: master #设置默认的数据源或者数据源组,默认值即为master
            strict: false #严格匹配数据源,默认false. true未匹配到指定数据源时抛异常,false使用默认数据源
            datasource:
                master:
                    driver-class-name: com.mysql.cj.jdbc.Driver
                    url: jdbc:mysql:///website?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
                    username: root
                    password: root
                    druid:
                        # 初始连接数
                        initialSize: 5
                        # 最小连接池数量
                        minIdle: 10
                        # 最大连接池数量
                        maxActive: 20
                        # 配置获取连接等待超时的时间
                        maxWait: 60000
                        # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
                        timeBetweenEvictionRunsMillis: 60000
                        # 配置一个连接在池中最小生存的时间，单位是毫秒
                        minEvictableIdleTimeMillis: 300000
                        # 配置一个连接在池中最大生存的时间，单位是毫秒
                        maxEvictableIdleTimeMillis: 900000
                        # 配置检测连接是否有效
                        validationQuery: SELECT 1 FROM DUAL
                        testWhileIdle: true
                        testOnBorrow: false
                        testOnReturn: false
                        webStatFilter:
                            enabled: true
                        statViewServlet:
                            enabled: true
                            # 设置白名单，不填则允许所有访问
                            allow:
                            url-pattern: /druid/*
                            # 控制台管理用户名和密码
                            login-username:
                            login-password:
                        filter:
                            stat:
                                enabled: true
                                # 慢SQL记录
                                log-slow-sql: true
                                slow-sql-millis: 1000
                                merge-sql: true
                            wall:
                                config:
                                    multi-statement-allow: true
    # redis 配置
    redis: #数据库
        # 地址
        host: 10.1.1.100
        # 端口，默认为6379
        port: 6379
        # 数据库索引
        database: 0
        # 密码
        password: root
        # 连接超时时间
        timeout: 500
        lettuce:
            pool:
                # 连接池中的最小空闲连接
                min-idle: 0
                # 连接池中的最大空闲连接
                max-idle: 8
                # 连接池的最大数据库连接数
                max-active: 8
                # #连接池最大阻塞等待时间（使用负值表示没有限制）
                max-wait: -1
                # 过期时间范围
                time-between-eviction-runs: -1
    # 配置rabbitMq 服务器
    rabbitmq:
        # 集群地址，用逗号分隔
        host: rabbit.shibo.com
        port: 5672
        username: admin
        password: 123456
        connection-timeout: 15000
minio: #文件服务器
    endpoint: 10.1.1.100
    port: 9000
    accessKey: shibo
    secretKey: shibo12#$
    secure: false
# 跨域配置
cors:
    allow-credentials: true
    allowed-origin: "*"
    allowed-header: "*"
    allowed-method: "*"
    allowed-path: "/**"

```

## 注意事项

### BeanUtils的使用

<font color="red">禁止使用Apache Beanutils进行属性的copy。</font>

> 由于 Apache下的BeanUtils对象拷贝性能太差，不建议使用，而且在阿里巴巴Java开发规约插件上也明确指出：
>
> Ali-Check | 避免用Apache Beanutils进行属性的copy。
> commons-beantutils 对于对象拷贝加了很多的检验，包括类型的转换，甚至还会检验对象所属的类的可访问性,可谓相当复杂，
> 这也造就了它的差劲的性能。



