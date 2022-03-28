import { Vuepress } from '@vuepress/client/lib/components/Vuepress'

const routeItems = [
  ["v-8daa1a0e","/",{"title":""},["/index.html","/index.md"]],
  ["v-5a154c81","/zh/guide/debug.html",{"title":""},["/zh/guide/debug","/zh/guide/debug.md"]],
  ["v-47357bdb","/zh/guide/",{"title":"指南"},["/zh/guide/index.html","/zh/guide/index.md"]],
  ["v-464ee4b0","/zh/guide/permissions.html",{"title":""},["/zh/guide/permissions","/zh/guide/permissions.md"]],
  ["v-0600c667","/zh/guide/prod.html",{"title":""},["/zh/guide/prod","/zh/guide/prod.md"]],
  ["v-68e21b05","/zh/guide/request.html",{"title":""},["/zh/guide/request","/zh/guide/request.md"]],
  ["v-2608b041","/zh/guide/standard.html",{"title":""},["/zh/guide/standard","/zh/guide/standard.md"]],
  ["v-00ec9be8","/zh/guide/test.html",{"title":""},["/zh/guide/test","/zh/guide/test.md"]],
  ["v-3706649a","/404.html",{"title":""},["/404"]],
]

export const pagesRoutes = routeItems.reduce(
  (result, [name, path, meta, redirects]) => {
    result.push(
      {
        name,
        path,
        component: Vuepress,
        meta,
      },
      ...redirects.map((item) => ({
        path: item,
        redirect: path,
      }))
    )
    return result
  },
  [
    {
      name: "404",
      path: "/:catchAll(.*)",
      component: Vuepress,
    }
  ]
)
