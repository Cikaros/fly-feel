## 日志系统log4j2

> Apache Log4j2 是对Log4j 的升级版本，参考了logback 的一些优秀的设计，并且修复了一些问题，因此带来了一些重大的提升，主要有：
>
> - 异常处理，在logback中，Appender中的异常不会被应用感知到，但是在log4j2中，提供了一些异常处理机制。
> - 性能提升，log4j2 相较于log4j 和 logback 都具有明显的性能提升，有18倍性能提升，后面会有官方测试的数据。
> - 自动重载配置，参考了logback的设计，当然会提供自动刷新参数配置，最实用的就是我们在生产上可以动态的修改日志的级别而不需要重启应用。
> - 无垃圾机制，log4j2 在大部分情况下，都可以使用其设计的一套无垃圾机制【对象重用、内存缓冲】，避免频繁的日志收集导致的 JVM GC。
>

本框架中排除了原生自带的Logback和log4j，使用了最新的log4j2，相较原来的log4j，异步处理性能上有很大的提升。

## 如何使用

只需在类中加入`private static final Logger log = LoggerFactory.getLogger(getClass());`即可。

`Controller`和`Service`中可直接使用(需要继承各自的基础类)。

## 日志等级

主要有以下几个级别：

- DEBUG Level指出细粒度信息事件对调试应用程序是非常有帮助的。
- INFO level表明 消息在粗粒度级别上突出强调应用程序的运行过程。
- WARN level表明会出现潜在错误的情形。
- ERROR level指出虽然发生错误事件，但仍然不影响系统的继续运行。
- FATAL level指出每个严重的错误事件将会导致应用程序的退出。

> Log4j建议只使用四个级别，优先级从高到低分别是FATAL, ERROR、WARN、INFO、DEBUG。通过在这里定义的级别，
>
> 您可以控制到应用程序中相应级别的日志信息的开关。比如在这里定义了INFO级别，则应用程序中所有DEBUG级别的日志信息将不被打印出来。
>
> 程序会打印高于或等于所设置级别的日志，设置的日志等级越高，打印出来的日志就越少。如果设置级别为INFO，
>
> 则优先级高于等于INFO级别（如：INFO、WARN、ERROR）的日志信息将可以被输出,小于该级别的如DEBUG将不会被输出。
>

## 日志格式

```yaml
%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}
```
下面是打印的日志

```text
Connected to the target VM, address: '127.0.0.1:13535', transport: 'socket'
  ████████ ██      ██ ██████
 ██░░░░░░ ░██     ░░ ░█░░░░██
░██       ░██      ██░█   ░██   ██████
░█████████░██████ ░██░██████   ██░░░░██
░░░░░░░░██░██░░░██░██░█░░░░ ██░██   ░██
       ░██░██  ░██░██░█    ░██░██   ░██
 ████████ ░██  ░██░██░███████ ░░██████
░░░░░░░░  ░░   ░░ ░░ ░░░░░░░   ░░░░░░
Spring Boot Version: 2.2.13.RELEASE
Spring Boot properties : dev

2021-08-26 15:05:19.748  INFO 16648 --- [  restartedMain] c.s.p.ShiBoApplication                   : Starting ShiBoApplication on DESKTOP-EIDS8O3 with PID 16648 (D:\Code\spring-boot-solution\spring-boot-project\target\classes started by HP in D:\Code\spring-boot-solution)
2021-08-26 15:05:19.762 DEBUG 16648 --- [  restartedMain] c.s.p.ShiBoApplication                   : Running with Spring Boot v2.2.13.RELEASE, Spring v5.2.12.RELEASE
2021-08-26 15:05:19.763  INFO 16648 --- [  restartedMain] c.s.p.ShiBoApplication                   : The following profiles are active: dev
2021-08-26 15:05:19.867  INFO 16648 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2021-08-26 15:05:19.867  INFO 16648 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2021-08-26 15:05:23.778 DEBUG 16648 --- [  restartedMain] c.s.f.e.h.s.EnumRegistrar                : 已扫描到Enum类：AuthorizedGrantType
2021-08-26 15:05:23.779 DEBUG 16648 --- [  restartedMain] c.s.f.e.h.s.EnumRegistrar                : 已扫描到Enum类：BucketType
2021-08-26 15:05:23.779 DEBUG 16648 --- [  restartedMain] c.s.f.e.h.s.EnumRegistrar                : 已扫描到Enum类：ComponentType
2021-08-26 15:05:23.780 DEBUG 16648 --- [  restartedMain] c.s.f.e.h.s.EnumRegistrar                : 已扫描到Enum类：EventType
2021-08-26 15:05:23.781 DEBUG 16648 --- [  restartedMain] c.s.f.e.h.s.EnumRegistrar                : 已扫描到Enum类：HttpStatus
2021-08-26 15:05:23.782 DEBUG 16648 --- [  restartedMain] c.s.f.e.h.s.EnumRegistrar                : 已扫描到Enum类：OperateType
2021-08-26 15:05:23.782 DEBUG 16648 --- [  restartedMain] c.s.f.e.h.s.EnumRegistrar                : 已扫描到Enum类：ResourceType
2021-08-26 15:05:23.783 DEBUG 16648 --- [  restartedMain] c.s.f.e.h.s.EnumRegistrar                : 已扫描到Enum类：UserType
2021-08-26 15:05:23.791 DEBUG 16648 --- [  restartedMain] c.s.f.c.SpringUtils                      : Bean[com.shibo.framework.jackson.EnumKeyObjectMapper]已注册到自定义容器！
2021-08-26 15:05:23.791 DEBUG 16648 --- [  restartedMain] c.s.f.c.SpringUtils                      : Bean[com.shibo.framework.jackson.EnumKeyValueObjectMapper]已注册到自定义容器！
2021-08-26 15:05:23.791 DEBUG 16648 --- [  restartedMain] c.s.f.c.SpringUtils                      : Bean[com.shibo.framework.jackson.EnumValueObjectMapper]已注册到自定义容器！
2021-08-26 15:05:23.794 DEBUG 16648 --- [  restartedMain] c.s.f.c.MybatisPlusConfig                : Could not determine auto-configuration package, automatic mapper scanning disabled.
2021-08-26 15:05:25.075  INFO 16648 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2021-08-26 15:05:25.076  INFO 16648 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2021-08-26 15:05:25.112  INFO 16648 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 15ms. Found 0 MongoDB repository interfaces.
2021-08-26 15:05:25.198  INFO 16648 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2021-08-26 15:05:25.207  INFO 16648 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data Redis repositories in DEFAULT mode.
2021-08-26 15:05:25.242  INFO 16648 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 1ms. Found 0 Redis repository interfaces.
2021-08-26 15:05:25.874 DEBUG 16648 --- [  restartedMain] c.s.f.c.SpringUtils                      : 接入ApplicationContext
2021-08-26 15:05:25.944 DEBUG 16648 --- [  restartedMain] c.s.f.c.SpringUtils                      : 创建子容器并继承Web父容器
2021-08-26 15:05:26.968  INFO 16648 --- [  restartedMain] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler@360fe1e9' of type [org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-08-26 15:05:27.009  INFO 16648 --- [  restartedMain] trationDelegate$BeanPostProcessorChecker : Bean 'methodSecurityMetadataSource' of type [org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-08-26 15:05:27.087  INFO 16648 --- [  restartedMain] trationDelegate$BeanPostProcessorChecker : Bean 'spring.redis-org.springframework.boot.autoconfigure.data.redis.RedisProperties' of type [org.springframework.boot.autoconfigure.data.redis.RedisProperties] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-08-26 15:05:27.113  INFO 16648 --- [  restartedMain] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.boot.autoconfigure.data.redis.LettuceConnectionConfiguration' of type [org.springframework.boot.autoconfigure.data.redis.LettuceConnectionConfiguration] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-08-26 15:05:27.431  INFO 16648 --- [  restartedMain] trationDelegate$BeanPostProcessorChecker : Bean 'lettuceClientResources' of type [io.lettuce.core.resource.DefaultClientResources] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-08-26 15:05:27.865  INFO 16648 --- [  restartedMain] trationDelegate$BeanPostProcessorChecker : Bean 'redisConnectionFactory' of type [org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-08-26 15:05:27.994  INFO 16648 --- [  restartedMain] trationDelegate$BeanPostProcessorChecker : Bean 'redisConfig' of type [com.shibo.framework.config.RedisConfig$$EnhancerBySpringCGLIB$$20765ed3] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2021-08-26 15:05:29.335  INFO 16648 --- [  restartedMain] o.s.b.w.e.t.TomcatWebServer              : Tomcat initialized with port(s): 8080 (http)
2021-08-26 15:05:29.359  INFO 16648 --- [  restartedMain] o.a.c.c.StandardService                  : Starting service [Tomcat]
2021-08-26 15:05:29.360  INFO 16648 --- [  restartedMain] o.a.c.c.StandardEngine                   : Starting Servlet engine: [Apache Tomcat/9.0.41]
2021-08-26 15:05:29.517  INFO 16648 --- [  restartedMain] o.a.c.c.C.[.[.[/]                        : Initializing Spring embedded WebApplicationContext
2021-08-26 15:05:29.518  INFO 16648 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 9650 ms
2021-08-26 15:05:29.824 DEBUG 16648 --- [  restartedMain] c.s.f.c.MybatisPlusConfig                : Not found configuration for registering mapper bean using @MapperScan, MapperFactoryBean and MapperScannerConfigurer.
2021-08-26 15:05:30.081 DEBUG 16648 --- [  restartedMain] c.s.f.i.m.SqlExecutorInterceptor         : 已加载SqlLog日志记录监听器
2021-08-26 15:05:36.532  INFO 16648 --- [  restartedMain] o.s.s.w.DefaultSecurityFilterChain       : Creating filter chain: OrRequestMatcher [requestMatchers=[Ant [pattern='/oauth/token'], Ant [pattern='/oauth/token_key'], Ant [pattern='/oauth/check_token']]], [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@3a8713de, org.springframework.security.web.context.SecurityContextPersistenceFilter@45efb8d4, org.springframework.security.web.header.HeaderWriterFilter@1285a664, org.springframework.security.web.authentication.logout.LogoutFilter@ba42a8f, com.shibo.framework.security.filter.CustomClientCredentialsTokenEndpointFilter@6e240daf, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@110c7762, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@6b35209b, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@239728df, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@6d698593, org.springframework.security.web.session.SessionManagementFilter@61c15adb, org.springframework.security.web.access.ExceptionTranslationFilter@58bc6f1e, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@66ba14c1]
2021-08-26 15:05:36.554  INFO 16648 --- [  restartedMain] o.s.s.w.DefaultSecurityFilterChain       : Creating filter chain: org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration$NotOAuthRequestMatcher@7aadbeb1, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@6c95c784, org.springframework.security.web.context.SecurityContextPersistenceFilter@1f089c6, org.springframework.security.web.header.HeaderWriterFilter@65a253f3, org.springframework.web.filter.CorsFilter@348a249a, org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter@28793eb3, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@763595bf, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@17f7d41, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@6af11f4, org.springframework.security.web.session.SessionManagementFilter@10310517, org.springframework.security.web.access.ExceptionTranslationFilter@5c0576f9, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@47ff429]
2021-08-26 15:05:36.658  INFO 16648 --- [  restartedMain] o.s.s.w.DefaultSecurityFilterChain       : Creating filter chain: any request, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@2783aa3b, org.springframework.security.web.context.SecurityContextPersistenceFilter@98c4ec1, org.springframework.security.web.header.HeaderWriterFilter@7a19e6fb, org.springframework.security.web.csrf.CsrfFilter@7f738f0d, org.springframework.security.web.authentication.logout.LogoutFilter@2ca0ea46, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@40f6a284, org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@78985bd, org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter@47ade8bc, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@5a7f82d1, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@61f3d4a1, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@5468c85a, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@78a54d36, org.springframework.security.web.session.SessionManagementFilter@3da1d0f, org.springframework.security.web.access.ExceptionTranslationFilter@4335744d, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@2e214615]
2021-08-26 15:05:37.111  INFO 16648 --- [  restartedMain] o.s.s.c.ThreadPoolTaskExecutor           : Initializing ExecutorService 'applicationTaskExecutor'
2021-08-26 15:05:37.414  INFO 16648 --- [  restartedMain] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page: class path resource [static/index.html]
2021-08-26 15:05:39.855  INFO 16648 --- [  restartedMain] o.m.d.cluster                            : Cluster created with settings {hosts=[10.1.1.100:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
2021-08-26 15:05:40.040  INFO 16648 --- [0.1.1.100:27017] o.m.d.connection                         : Opened connection [connectionId{localValue:1, serverValue:120}] to 10.1.1.100:27017
2021-08-26 15:05:40.047  INFO 16648 --- [0.1.1.100:27017] o.m.d.cluster                            : Monitor thread successfully connected to server with description ServerDescription{address=10.1.1.100:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[4, 4, 6]}, minWireVersion=0, maxWireVersion=9, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=5422000}
2021-08-26 15:05:42.308  INFO 16648 --- [  restartedMain] o.s.s.c.ThreadPoolTaskScheduler          : Initializing ExecutorService 'taskScheduler'
2021-08-26 15:05:42.769  INFO 16648 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2021-08-26 15:05:42.972  INFO 16648 --- [  restartedMain] o.s.b.w.e.t.TomcatWebServer              : Tomcat started on port(s): 8080 (http) with context path ''
2021-08-26 15:05:42.977  INFO 16648 --- [  restartedMain] c.s.p.ShiBoApplication                   : Started ShiBoApplication in 24.538 seconds (JVM running for 28.113)
2021-08-26 15:05:42.981  INFO 16648 --- [  restartedMain] c.s.p.ShiBoApplication                   : ShiBoApplication 启动成功！
2021-08-26 15:05:42.981 DEBUG 16648 --- [  restartedMain] c.s.a.c.AdminConfig                      : 已加载Admin管理平台......
```


## 编码注意事项

<font color='red'>必要的日志请用INFO级别的日志打印，调试日志请使用DEBUG级别打印。错误日志则直接使用ERROR级别打印。</font>
```java
public class Test{

    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public void method(){
        String args = "日志参数";
        Exception ex = new Exception();
        log.info("必要日志：{}",args);
        log.debug("调试日志：{}",args);
        log.error("错误日志：{}",args,ex);
    }

}
```