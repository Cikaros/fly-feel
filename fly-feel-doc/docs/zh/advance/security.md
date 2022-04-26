# Security的使用

## 基础

若仅需使用最基础的权限验证，可在`pom.xml`中依赖`fly-feel-security-core`与`fly-feel-define-impl`即可。

```xml

<dependencies>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>
    <!--权限核心-->
    <dependency>
        <groupId>io.gitee.cikaros</groupId>
        <artifactId>fly-feel-security-core</artifactId>
    </dependency>
    <!--权限核心的依赖库-->
    <dependency>
        <groupId>io.gitee.cikaros</groupId>
        <artifactId>fly-feel-define-impl</artifactId>
    </dependency>
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencies>
```

在入口实现方面可参照以下实现：

```java

@SpringBootApplication
@MapperScan("io.gitee.define.mapper") //为导入已实现的ORM
public class FlyFeelBasisServer {

    public static void main(String[] args) {
        SpringApplication.run(FlyFeelBasisServer.class, args);
    }

}
```

配置文件如下：

```yml
spring:
  main:
    allow-bean-definition-overriding: true
  messages:
    basename: i18n/Message
  datasource:
    url: jdbc:mysql://数据库IP/fly-feel
    username: 数据库账号
    password: 数据库密码
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.zaxxer.hikari.HikariDataSource
    hikari:
      maximum-pool-size: 10
      minimum-idle: 10
      idle-timeout: 600000
      connection-timeout: 30000
      max-lifetime: 1800000
  jackson:
    default-property-inclusion: non_null
  freemarker:
    suffix: .html
  mvc:
    throw-exception-if-no-handler-found: true
mybatis:
  mapper-locations:
    - classpath*:mapper/**/*Mapper.xml
```

若需要自定义密码加密方式，可自行加入@Bean `PasswordEncode`。例如：

```java

@SpringBootApplication
@MapperScan("io.gitee.define.mapper") //为导入已实现的ORM
public class FlyFeelBasisServer {

    public static void main(String[] args) {
        SpringApplication.run(FlyFeelBasisServer.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
```

## 资源服务器(Resource Server) 和 授权服务器(Licenses Server)

### 授权服务器(Licenses Server)

若想实现一个授权服务器则需要引入`fly-feel-security-license`与`fly-feel-define-impl`。

```xml

<dependencies>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>
    <!--授权核心-->
    <dependency>
        <groupId>io.gitee.cikaros</groupId>
        <artifactId>fly-feel-security-license</artifactId>
    </dependency>
    <!--授权核心依赖库-->
    <dependency>
        <groupId>io.gitee.cikaros</groupId>
        <artifactId>fly-feel-define-impl</artifactId>
    </dependency>
    <dependency>
        <groupId>com.zaxxer</groupId>
        <artifactId>HikariCP</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencies>
```

入口具体实现如下：

```java

@SpringBootApplication
@MapperScan("io.gitee.define.mapper")
public class FlyFeelLicenseServer {

    public static void main(String[] args) {
        SpringApplication.run(FlyFeelLicenseServer.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
```

```yml
spring:
  main:
    allow-bean-definition-overriding: true
  messages:
    basename: i18n/Message
  datasource:
    url: jdbc:mysql://数据库IP/fly-feel
    username: 数据库账号
    password: 数据库密码
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.zaxxer.hikari.HikariDataSource
    hikari:
      maximum-pool-size: 10
      minimum-idle: 10
      idle-timeout: 600000
      connection-timeout: 30000
      max-lifetime: 1800000
  jackson:
    default-property-inclusion: non_null
  freemarker:
    suffix: .html
  mvc:
    throw-exception-if-no-handler-found: true
  security:
    oauth2:
      resourceserver:
        opaquetoken:
          client-id: 客户端id
          client-secret: 客户端密钥
mybatis:
  mapper-locations:
    - classpath*:mapper/**/*Mapper.xml
```

### 资源服务器(Resource Server)

若想实现一个资源服务器则需要引入`fly-feel-security-resource`与`fly-feel-define-impl`。

```xml

<dependencies>
    <!--资源核心-->
    <dependency>
        <groupId>io.gitee.cikaros</groupId>
        <artifactId>fly-feel-security-resource</artifactId>
    </dependency>
</dependencies>
```

入口具体实现如下：

```java

@SpringBootApplication
public class FlyFeelResourceServer {

    public static void main(String[] args) {
        SpringApplication.run(FlyFeelResourceServer.class, args);
    }

}
```

```yml
spring:
  jackson:
    default-property-inclusion: non_null
  freemarker:
    suffix: .html
  mvc:
    throw-exception-if-no-handler-found: true
  security:
    oauth2:
      resourceserver:
        opaquetoken:
          client-id: 客户端id
          client-secret: 客户端密钥
          introspection-uri: http://授权服务器IP:授权服务器端口/oauth/check_token
```