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
        darkMode: true,
        repo: 'https://gitee.com/cikaros/fly-feel',
        repoLabel: 'Gitee',
        // 默认值是 true 。设置为 false 来禁用所有页面的 下一篇 链接
        nextLinks: true,
        // 默认值是 true 。设置为 false 来禁用所有页面的 上一篇 链接
        prevLinks: true,
        sidebarDepth: 2,
        sidebar: [
            {
                text: "开始",
                link: "/zh/guide/",
                children: [
                    {
                        text: "规范说明文档",
                        link: "standard"
                    },
                    {
                        text: "权限使用文档",
                        link: "permissions"
                    },
                    {
                        text: "测试说明文档",
                        link: "test"
                    },
                    {
                        text: "调试说明文档",
                        link: "debug"
                    },
                    {
                        text: "部署说明文档",
                        link: "prod"
                    },
                    {
                        text: "请求码说明文档",
                        link: "request"
                    }
                ]
            }
        ],
        navbar: [
            {
                text: '关于作者',
                link: 'https://cikaros.gitee.io/about/'
            }
        ],
    },
    alias: {
        '@alias': path.resolve(__dirname, 'docs/assert'),
    }
}