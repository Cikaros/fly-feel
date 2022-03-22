module.exports = {
    // 站点配置
    base: '/fly-feel/',
    lang: 'zh-CN',
    title: 'fly-feel框架',
    description: '一个集成了常用功能和业务需求的开发框架。',
    head: [['link', {rel: 'icon', href: './images/favicon.svg'}]],
    // 主题和它的配置
    theme: '@vuepress/theme-default',
    themeConfig: {
        logo: '/images/favicon.svg',
        home: '/',
        darkMode: true,
        repo: 'https://gitee.com/cikaros',
        repoLabel: 'Gitee',
        // 默认值是 true 。设置为 false 来禁用所有页面的 下一篇 链接
        nextLinks: true,
        // 默认值是 true 。设置为 false 来禁用所有页面的 上一篇 链接
        prevLinks: true,
        navbar: [
            // {
            //     text: '指南',
            //     link: '/zh/guide/'
            // },
            {
                text: '关于作者',
                link: 'https://cikaros.gitee.io/about/'
            }
        ],
    }
}