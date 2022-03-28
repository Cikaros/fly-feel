export const themeData = {
  "logo": "/images/favicon.svg",
  "home": "/",
  "darkMode": true,
  "repo": "https://gitee.com/cikaros/fly-feel",
  "repoLabel": "Gitee",
  "nextLinks": true,
  "prevLinks": true,
  "sidebarDepth": 2,
  "sidebar": [
    {
      "text": "开始",
      "link": "/guide/",
      "children": [
        {
          "text": "规范说明文档",
          "link": "standard"
        },
        {
          "text": "权限使用文档",
          "link": "permissions"
        },
        {
          "text": "测试说明文档",
          "link": "test"
        },
        {
          "text": "调试说明文档",
          "link": "debug"
        },
        {
          "text": "部署说明文档",
          "link": "prod"
        },
        {
          "text": "请求码说明文档",
          "link": "request"
        }
      ]
    }
  ],
  "navbar": [
    {
      "text": "关于作者",
      "link": "https://cikaros.gitee.io/about/"
    }
  ],
  "locales": {
    "/": {
      "selectLanguageName": "English"
    }
  },
  "selectLanguageText": "Languages",
  "selectLanguageAriaLabel": "Select language",
  "editLink": true,
  "editLinkText": "Edit this page",
  "lastUpdated": true,
  "lastUpdatedText": "Last Updated",
  "contributors": true,
  "contributorsText": "Contributors",
  "notFound": [
    "There's nothing here.",
    "How did we get here?",
    "That's a Four-Oh-Four.",
    "Looks like we've got some broken links."
  ],
  "backToHome": "Take me home",
  "openInNewWindow": "open in new window",
  "toggleDarkMode": "toggle dark mode",
  "toggleSidebar": "toggle sidebar"
}

if (import.meta.webpackHot) {
  import.meta.webpackHot.accept()
  if (__VUE_HMR_RUNTIME__.updateThemeData) {
    __VUE_HMR_RUNTIME__.updateThemeData(themeData)
  }
}

if (import.meta.hot) {
  import.meta.hot.accept(({ themeData }) => {
    __VUE_HMR_RUNTIME__.updateThemeData(themeData)
  })
}
