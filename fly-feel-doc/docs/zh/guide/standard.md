## 为什么要制定规范

制定规范的初衷也是为了解决一下问题：

1. 团队内部协作
2. 可追溯的更新版本
3. 组件化设计，可复用性强
4. 通过组件化设计，提升可复用性，来提升开发效率
5. 如果是多个业务线并行，统一的规范能够起到统一的作用

简单来说就是为了统一任务目标的解决方式和手段，便于团队合作完成同一个任务或同一类任务。

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
- 遇到错误统一抛异常，将错误交由统一异常处理类去处理。

#### 注入方式

通过`@Autowired`注入时,精确指定注入方式。

#### 统一Controller

##### BaseController

```java
/**
 * 基础Controller
 *
 * @author Cikaros
 * @date 2021/7/7
 */
@Controller
@Scope("request")
public class BaseController {

    protected final Logger log = LoggerFactory.getLogger(BaseController.class);

    protected I18nService i18n;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @Autowired
    public void setI18n(I18nService i18n) {
        this.i18n = i18n;
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return String.format("redirect:%s", url);
    }

}
```

##### BaseRestController

```java
/**
 * 基础Controller
 *
 * @author Cikaros
 * @date 2021/7/7
 */
@RestController
@Scope("request")
public class BaseRestController {

    protected final Logger log = LoggerFactory.getLogger(BaseRestController.class);

    protected I18nService i18n;

    @Autowired
    public void setI18n(I18nService i18n) {
        this.i18n = i18n;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected RestResult toRest(int rows) {
        return rows > 0 ? RestResult.success(i18n.get("io.gitee.core.controller.BaseController.toRest.success", rows)) : RestResult.error(i18n.get("io.gitee.core.controller.BaseController.fail"));
    }

    protected RestResult toRest(int flag, BaseModel data) {
        return toRest(flag != 1, data);
    }

    protected RestResult toRest(boolean flag, BaseModel data) {
        return toRest(flag, data, i18n.get("io.gitee.core.controller.BaseController.success"), i18n.get("io.gitee.core.controller.BaseController.fail"));
    }

    protected RestResult toRest(int flag, BaseModel data, String success, String fail) {
        return toRest(flag != 1, data, success, fail);
    }

    protected RestResult toRest(boolean flag, BaseModel data, String success, String fail) {
        return flag ? RestResult.error(data, success) : RestResult.success(data, fail);
    }

}
```

##### 自定义Controller

自定义Controller需继承`BaseController`或`BaseRestController`

内置log日志输出。调试信息用`log.debug()`、必要的打印信息用`log.info()`，请不要删除debug是留下的日志语句，该日志可在配置文件中设置打印类型来关闭debug信息。

#### 统一Service

```java
/**
 * 基础业务类
 *
 * @author Cikaros
 * @date 2021/7/8
 */
@Service
public class BaseService {

    protected final Logger log = LoggerFactory.getLogger(BaseService.class);

    protected I18nService i18n;

    @Autowired
    public void setI18n(I18nService i18n) {
        this.i18n = i18n;
    }

}
```

自定义Service实现类需集成`BaseService`

内置log日志输出，同上。

内置国际化支持。可使用`i18n.message()`方法，获取`resources/i18n/Message*`中的国际化配置文件。

#### 统一实体

自定义Form实体统一继成`BaseForm`

自定义Query实体统一继承`BaseQuery`

需要在已有的实体上扩充属性，均使用继承的形式去书写

#### 统一业务Exception

```java
/**
 * 统一自定义异常类
 *
 * @author Cikaros
 * @date 2021/7/12
 */
public class BaseException extends RuntimeException {

    protected static ResourceBundle i18n = ResourceBundle.getBundle("i18n/Exception");

    public BaseException(String message) {
        super(MessageFormat.format(i18n.getString("io.gitee.core.exception.BaseException"), message));
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String message, Throwable throwable) {
        super(MessageFormat.format(i18n.getString("io.gitee.core.exception.BaseException"), message), throwable);
    }

}
```

自定义的异常统一继承`BaseException`。

需要处理自己的自定义异常类，请自行构建异常处理类。

内置国际化支持，这里的国际化支持路径为`resource/i18n/Exception*`。

#### 统一Mapper

Mapper实现方式同Mybatis plus。更多详情请查看[Mybatis](https://mybatis.org/mybatis-3/zh/index.html)和[Mybtais
Plus](https://mybatis.plus/)官网。

<span color="blue">这里需要注意的是：</span>**不要使用注解形式开发！**

<span color="green">注解开发虽然快速高效，但后期优化和阅读源码都很困难。所以请务必使用Mybatis提供的XML形式开发。</span>

<span color="red">禁止使用Mybatis Plus提供的`QueryWrapper`，这种实现方式使得代码臃肿、难以阅读且不便维护。</span>

<span color="red">注：</span>在Service中引入Mapper类时若此时的Mapper只存在一个实例则直接使用注解注入即可。若该Mapper还存在子接口，则必须使用`@Qualifier`
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

#### 数据校验

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




