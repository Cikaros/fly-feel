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
"C:\Program Files\Java\jdk1.8.0_211\bin\java.exe" -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always "-javaagent:D:\JetBrains\IntelliJ IDEA 2021.2.4\lib\idea_rt.jar=1636:D:\JetBrains\IntelliJ IDEA 2021.2.4\bin" -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_211\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_211\jre\lib\rt.jar;E:\Project\fly-feel-tests\fly-feel-server-simple\target\classes;E:\Project\fly-feel\fly-feel-core\target\classes;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter-web\2.3.12.RELEASE\spring-boot-starter-web-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter-json\2.3.12.RELEASE\spring-boot-starter-json-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.11.4\jackson-datatype-jdk8-2.11.4.jar;E:\Temp\.m2\repo\com\fasterxml\jackson\module\jackson-module-parameter-names\2.11.4\jackson-module-parameter-names-2.11.4.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter-tomcat\2.3.12.RELEASE\spring-boot-starter-tomcat-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\apache\tomcat\embed\tomcat-embed-core\9.0.46\tomcat-embed-core-9.0.46.jar;E:\Temp\.m2\repo\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.46\tomcat-embed-websocket-9.0.46.jar;E:\Temp\.m2\repo\org\springframework\spring-web\5.2.19.RELEASE\spring-web-5.2.19.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\spring-beans\5.2.19.RELEASE\spring-beans-5.2.19.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\spring-webmvc\5.2.19.RELEASE\spring-webmvc-5.2.19.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\spring-expression\5.2.19.RELEASE\spring-expression-5.2.19.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter-log4j2\2.3.12.RELEASE\spring-boot-starter-log4j2-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\apache\logging\log4j\log4j-slf4j-impl\2.13.3\log4j-slf4j-impl-2.13.3.jar;E:\Temp\.m2\repo\org\apache\logging\log4j\log4j-api\2.15.0\log4j-api-2.15.0.jar;E:\Temp\.m2\repo\org\apache\logging\log4j\log4j-core\2.15.0\log4j-core-2.15.0.jar;E:\Temp\.m2\repo\org\apache\logging\log4j\log4j-jul\2.13.3\log4j-jul-2.13.3.jar;E:\Temp\.m2\repo\org\slf4j\jul-to-slf4j\1.7.30\jul-to-slf4j-1.7.30.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter-aop\2.3.12.RELEASE\spring-boot-starter-aop-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\spring-aop\5.2.19.RELEASE\spring-aop-5.2.19.RELEASE.jar;E:\Temp\.m2\repo\org\aspectj\aspectjweaver\1.8.0\aspectjweaver-1.8.0.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter-freemarker\2.3.12.RELEASE\spring-boot-starter-freemarker-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\freemarker\freemarker\2.3.31\freemarker-2.3.31.jar;E:\Temp\.m2\repo\org\springframework\spring-context-support\5.2.19.RELEASE\spring-context-support-5.2.19.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter-validation\2.3.12.RELEASE\spring-boot-starter-validation-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\glassfish\jakarta.el\3.0.3\jakarta.el-3.0.3.jar;E:\Temp\.m2\repo\org\hibernate\validator\hibernate-validator\6.1.7.Final\hibernate-validator-6.1.7.Final.jar;E:\Temp\.m2\repo\jakarta\validation\jakarta.validation-api\2.0.2\jakarta.validation-api-2.0.2.jar;E:\Temp\.m2\repo\org\jboss\logging\jboss-logging\3.4.2.Final\jboss-logging-3.4.2.Final.jar;E:\Temp\.m2\repo\com\fasterxml\classmate\1.5.1\classmate-1.5.1.jar;E:\Temp\.m2\repo\uk\co\jemos\podam\podam\7.2.7.RELEASE\podam-7.2.7.RELEASE.jar;E:\Temp\.m2\repo\net\jcip\jcip-annotations\1.0\jcip-annotations-1.0.jar;E:\Temp\.m2\repo\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;E:\Temp\.m2\repo\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;E:\Temp\.m2\repo\javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar;E:\Temp\.m2\repo\org\apache\commons\commons-lang3\3.12.0\commons-lang3-3.12.0.jar;E:\Temp\.m2\repo\org\springframework\spring-core\5.2.19.RELEASE\spring-core-5.2.19.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\spring-jcl\5.2.15.RELEASE\spring-jcl-5.2.15.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter-actuator\2.3.12.RELEASE\spring-boot-starter-actuator-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-starter\2.3.12.RELEASE\spring-boot-starter-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;E:\Temp\.m2\repo\org\yaml\snakeyaml\1.26\snakeyaml-1.26.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-actuator-autoconfigure\2.3.12.RELEASE\spring-boot-actuator-autoconfigure-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-actuator\2.3.12.RELEASE\spring-boot-actuator-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\com\fasterxml\jackson\core\jackson-databind\2.10.5\jackson-databind-2.10.5.jar;E:\Temp\.m2\repo\com\fasterxml\jackson\core\jackson-annotations\2.10.5\jackson-annotations-2.10.5.jar;E:\Temp\.m2\repo\com\fasterxml\jackson\core\jackson-core\2.10.5\jackson-core-2.10.5.jar;E:\Temp\.m2\repo\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.11.4\jackson-datatype-jsr310-2.11.4.jar;E:\Temp\.m2\repo\io\micrometer\micrometer-core\1.5.14\micrometer-core-1.5.14.jar;E:\Temp\.m2\repo\org\hdrhistogram\HdrHistogram\2.1.12\HdrHistogram-2.1.12.jar;E:\Temp\.m2\repo\org\latencyutils\LatencyUtils\2.0.3\LatencyUtils-2.0.3.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-devtools\2.3.12.RELEASE\spring-boot-devtools-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot\2.3.12.RELEASE\spring-boot-2.3.12.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\spring-context\5.2.19.RELEASE\spring-context-5.2.19.RELEASE.jar;E:\Temp\.m2\repo\org\springframework\boot\spring-boot-autoconfigure\2.3.12.RELEASE\spring-boot-autoconfigure-2.3.12.RELEASE.jar" io.gitee.tests.FlyFeelSimpleServer
 ████████  ██          ████████                  ██
░██░░░░░  ░██  ██   ██░██░░░░░                  ░██
░██       ░██ ░░██ ██ ░██        █████   █████  ░██
░███████  ░██  ░░███  ░███████  ██░░░██ ██░░░██ ░██
░██░░░░   ░██   ░██   ░██░░░░  ░███████░███████ ░██
░██       ░██   ██    ░██      ░██░░░░ ░██░░░░  ░██
░██       ███  ██     ░██      ░░██████░░██████ ███
░░       ░░░  ░░      ░░        ░░░░░░  ░░░░░░ ░░░
Spring Boot Version: 2.3.12.RELEASE
Spring Boot properties :  unknown

2022-04-02 14:08:50.946  INFO 30640 --- [  restartedMain] i.g.t.FlyFeelSimpleServer                : Starting FlyFeelSimpleServer on Cikaros-PC with PID 30640 (E:\Project\fly-feel-tests\fly-feel-server-simple\target\classes started by Cikaros in E:\Project\fly-feel)
2022-04-02 14:08:50.950 DEBUG 30640 --- [  restartedMain] i.g.t.FlyFeelSimpleServer                : Running with Spring Boot v2.3.12.RELEASE, Spring v5.2.19.RELEASE
2022-04-02 14:08:50.950  INFO 30640 --- [  restartedMain] i.g.t.FlyFeelSimpleServer                : No active profile set, falling back to default profiles: default
2022-04-02 14:08:50.981  INFO 30640 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2022-04-02 14:08:50.981  INFO 30640 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2022-04-02 14:08:51.622  INFO 30640 --- [  restartedMain] o.s.b.w.e.t.TomcatWebServer              : Tomcat initialized with port(s): 8080 (http)
2022-04-02 14:08:51.627  INFO 30640 --- [  restartedMain] o.a.c.c.StandardService                  : Starting service [Tomcat]
2022-04-02 14:08:51.627  INFO 30640 --- [  restartedMain] o.a.c.c.StandardEngine                   : Starting Servlet engine: [Apache Tomcat/9.0.46]
2022-04-02 14:08:51.674  INFO 30640 --- [  restartedMain] o.a.c.c.C.[.[.[/]                        : Initializing Spring embedded WebApplicationContext
2022-04-02 14:08:51.674  INFO 30640 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 692 ms
2022-04-02 14:08:51.850  INFO 30640 --- [  restartedMain] o.s.s.c.ThreadPoolTaskExecutor           : Initializing ExecutorService 'applicationTaskExecutor'
2022-04-02 14:08:51.888  INFO 30640 --- [  restartedMain] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page template: index
2022-04-02 14:08:52.033  INFO 30640 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2022-04-02 14:08:52.036  INFO 30640 --- [  restartedMain] o.s.b.a.e.w.EndpointLinksResolver        : Exposing 2 endpoint(s) beneath base path '/actuator'
2022-04-02 14:08:52.067  INFO 30640 --- [  restartedMain] o.s.b.w.e.t.TomcatWebServer              : Tomcat started on port(s): 8080 (http) with context path ''
2022-04-02 14:08:52.076  INFO 30640 --- [  restartedMain] i.g.t.FlyFeelSimpleServer                : Started FlyFeelSimpleServer in 1.376 seconds (JVM running for 2.22)
2022-04-02 14:08:52.154 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 用户访问地址: /fly-feel/__vite_ping, 来路地址: 127.0.0.1
2022-04-02 14:08:52.154 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: host = localhost:8080
2022-04-02 14:08:52.154 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: connection = keep-alive
2022-04-02 14:08:52.154 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: sec-ch-ua = " Not A;Brand";v="99", "Chromium";v="100", "Google Chrome";v="100"
2022-04-02 14:08:52.154 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: sec-ch-ua-mobile = ?0
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: user-agent = Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.60 Safari/537.36
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: sec-ch-ua-platform = "Windows"
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: accept = */*
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: sec-fetch-site = same-origin
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: sec-fetch-mode = cors
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: sec-fetch-dest = empty
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: referer = http://localhost:8080/fly-feel/zh/guide/permissions.html
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: accept-encoding = gzip, deflate, br
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: accept-language = zh-CN,zh;q=0.9
2022-04-02 14:08:52.155 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求头: cookie = Idea-b24acdc4=863c3d54-56af-4ace-a768-a1b020174bf4
2022-04-02 14:08:52.158  INFO 30640 --- [nio-8080-exec-1] o.a.c.c.C.[.[.[/]                        : Initializing Spring DispatcherServlet 'dispatcherServlet'
2022-04-02 14:08:52.158  INFO 30640 --- [nio-8080-exec-1] o.s.w.s.DispatcherServlet                : Initializing Servlet 'dispatcherServlet'
2022-04-02 14:08:52.161  INFO 30640 --- [nio-8080-exec-1] o.s.w.s.DispatcherServlet                : Completed initialization in 3 ms
2022-04-02 14:08:52.212 DEBUG 30640 --- [nio-8080-exec-1] i.g.c.l.RequestListener                  : 请求处理结束. 处理耗时: 60
```


## 编码注意事项

<span color='red'>必要的日志请用INFO级别的日志打印，调试日志请使用DEBUG级别打印。错误日志则直接使用ERROR级别打印。</span>
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