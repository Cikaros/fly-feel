
# 指南

## 介绍

为快速搭建开发环境所做的框架，内部涵盖大部分情况下的解决方案。

主要有以下特点：

- XSS防护
- 跨域处理
- 提供基础的业务支撑
- 完备的权限管理体系
- 基于Oauth2的权限服务与资源服务
- 验证码

### 模块介绍

目前主要有以下可使用的模块

- fly-feel-dependencies 版本管理
- fly-feel-core 核心
- fly-feel-define 基础业务定义
- fly-feel-define-impl 基础业务实现
- fly-feel-test 测试
- fly-feel-verify 验证码
- fly-feel-security 安全

### fly-feel-dependencies

为方便开发者管理项目依赖专门实现的统一的依赖管理 _Pom_。

### fly-feel-core

fly-feel项目基础核心部件，内部提供了最基础的一些规范和部件。

### fly-feel-define

定义了基础的框架实体和业务接口。

### fly-feel-define-impl

对业务接口的相应实现。

### fly-feel-test

提供了默认测试父类，并提供了数据生成工具 _podam_。

### fly-feel-verify

提供常用的验证码生成。

### fly-feel-security

提供了最基础的安全机制，包括xss,cors,授权服务器和资源服务器等。

## 快速上手

首先创建一个maven项目，并在 `pom.xml` 中加入以下代码。

```xml

<project>
    <!--忽略上述标签-->

    <dependencyManagement>
        <dependencies>
            <!--版本控制-->
            <dependency>
                <groupId>io.gitee.cikaros</groupId>
                <artifactId>fly-feel-dependencies</artifactId>
                <version>2.0.2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!--fly-feel核心-->
        <dependency>
            <groupId>io.gitee.cikaros</groupId>
            <artifactId>fly-feel-core</artifactId>
        </dependency>
    </dependencies>

    <!--忽略下述标签-->
</project>
```

创建一个程序入口类，并写入以下代码：

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基础服务器
 *
 * @author Cikaros
 * @date 2022/3/22
 * @since v1.0
 */
@SpringBootApplication
public class FlyFeelSimpleServer {

    public static void main(String[] args) {
        SpringApplication.run(FlyFeelSimpleServer.class, args);
    }

}
```

在`resources`中新建`application.yml`文件，并在内部书写以下配置：

```yaml
spring:
  messages:
    basename: i18n/Message
  freemarker:
    suffix: .html
  mvc:
    throw-exception-if-no-handler-found: true
```

运行成功后，你将看到如下页面:

![首页效果](/images/effect_index.png)

若您还有其他较为复杂的项目要求，请参照 [项目样例](https://gitee.com/cikaros/fly-feel-tests) 。
