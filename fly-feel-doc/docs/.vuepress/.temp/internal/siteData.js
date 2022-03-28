export const siteData = {
  "base": "/fly-feel/",
  "lang": "zh-CN",
  "title": "fly-feel框架",
  "description": "一个集成了常用功能和业务需求的开发框架。",
  "head": [
    [
      "link",
      {
        "rel": "icon",
        "href": "/fly-feel/images/favicon.svg"
      }
    ]
  ],
  "locales": {}
}

if (import.meta.webpackHot) {
  import.meta.webpackHot.accept()
  if (__VUE_HMR_RUNTIME__.updateSiteData) {
    __VUE_HMR_RUNTIME__.updateSiteData(siteData)
  }
}

if (import.meta.hot) {
  import.meta.hot.accept(({ siteData }) => {
    __VUE_HMR_RUNTIME__.updateSiteData(siteData)
  })
}
