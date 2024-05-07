const path = require('path')
module.exports = {
    // 站点配置
    base: '/fly-feel/',
    lang: 'zh-CN',
    title: 'fly-feel框架',
    description: '一个集成了常用功能和业务需求的开发框架。',
    head: [['link', {rel: 'icon', href: '/fly-feel/images/favicon.svg'}]],
    // 主题和它的配置
    theme: '@vuepress/theme-default',
    themeConfig: {
        logo: '/images/favicon.svg',
        home: '/',
        darkMode: false,
        repo: 'https://github.com/cikaros/fly-feel',
        repoLabel: 'Github',
        sidebarDepth: 2,
        navbar: [
            {
                text: '首页',
                link: '/'
            },
            {
                text: '指南',
                children: [
                    {
                        text: "介绍",
                        link: "/zh/guide/",
                        activeMatch: '^/zh/guide/$'
                    },
                    {
                        text: "规范说明文档",
                        link: "/zh/guide/standard",
                        activeMatch: '^/zh/guide/standard'
                    },
                    {
                        text: "测试说明文档",
                        link: "/zh/guide/test",
                        activeMatch: '^/zh/guide/test'
                    },
                    {
                        text: "调试说明文档",
                        link: "/zh/guide/debug",
                        activeMatch: '^/zh/guide/debug'
                    },
                    {
                        text: "部署说明文档",
                        link: "/zh/guide/prod",
                        activeMatch: '^/zh/guide/prod'
                    },
                    {
                        text: "请求码说明文档",
                        link: "/zh/guide/request",
                        activeMatch: '^/zh/guide/request'
                    }
                ]
            }, {
                text: '进阶',
                children: [
                    {
                        text: "介绍",
                        link: "/zh/advance/",
                        activeMatch: '^/zh/advance/$'
                    },
                    {
                        text: "Controller接口实现",
                        link: "/zh/advance/controller",
                        activeMatch: '^/zh/advance/controller'
                    },
                    {
                        text: "权限实现",
                        link: "/zh/advance/security",
                        activeMatch: '^/zh/advance/security'
                    }
                ]
            },
            {
                text: '模块',
                children: [
                    {
                        text: "介绍",
                        link: "/zh/modules/",
                        activeMatch: '^/zh/modules/$'
                    },
                    {
                        text: "fly-feel-core",
                        link: "/zh/modules/fly-feel-core",
                        activeMatch: '^/zh/modules/fly-feel-core'
                    }
                ]
            },
            {
                text: '关于作者',
                link: '/about/'
            }
        ],
    },
    alias: {
        '@alias': path.resolve(__dirname, 'docs/assert'),
    }
}
