# 模块简介

目前主要有以下可使用的模块

- fly-feel-dependencies 版本管理
- fly-feel-core 核心
- fly-feel-define 基础业务定义
- fly-feel-define-impl 基础业务实现
- fly-feel-test 测试
- fly-feel-verify 验证码
- fly-feel-security 安全

## 模块依赖关系介绍

![首页效果](/images/modules.svg)

# 模块目录

## fly-feel-dependencies

为方便开发者管理项目依赖专门实现的统一的依赖管理 _Pom_。

## fly-feel-core

fly-feel项目基础核心部件，内部提供了最基础的一些规范和部件。

## fly-feel-define

定义了基础的框架实体和业务接口。

## fly-feel-define-impl

对业务接口的相应实现。

## fly-feel-test

提供了默认测试父类，并提供了数据生成工具 _podam_。

## fly-feel-verify

提供常用的验证码生成。

## fly-feel-security

提供了最基础的安全机制，包括xss,cors,授权服务器和资源服务器等。