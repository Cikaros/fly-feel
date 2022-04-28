import {createRouter, createWebHistory} from 'vue-router';
import apis from '../net/api';
import {accessToken, removeToken} from "../net/token";
import {resolveComponent} from "vue";

// const Index = () => import('views/Index');
// const Dashboard = () => import('views/Dashboard');
// const Login = () => import('views/Login');
// const ComponentResource = () => import('views/resource/ComponentResource');
// const ServerResource = () => import('views/resource/ServerResource');
// const ResourceService = () => import('views/resource/ResourceService');
// const AuthorityManagement = () => import('views/authority/AuthorityManagement');
// const ServerLog = () => import('views/log/ServerLog');
// const SqlLog = () => import('views/log/SqlLog');
// const RoleList = () => import('views/role/RoleList');
// const UserList = () => import('views/user/UserList');
// const TokenList = () => import('views/user/TokenList');
// const ClientList = () => import('views/user/ClientList')
// const DepartmentList = () => import('views/department/DepartmentList');
const Index = () => import('../views/Index.vue');

const routes = [
    {
        path: '/',
        components: Index
    }
];
// const routes = [
//     {
//         path: '/',
//         redirect: '/dashboard'
//     },
//     {
//         path: '/index',
//         component: Index,
//         children: [
//             {
//                 path: '/dashboard',
//                 name: 'dashboard',
//                 component: Dashboard,
//                 meta: {
//                     title: '首页'
//                 }
//             },
//             {
//                 path: '/resource/component',
//                 component: ComponentResource,
//                 meta: {
//                     title: '组件资源'
//                 }
//             },
//             {
//                 path: '/resource/server',
//                 component: ServerResource,
//                 meta: {
//                     title: '服务资源'
//                 }
//             },
//             {
//                 path: '/resource/service',
//                 component: ResourceService,
//                 meta: {
//                     title: '资源服务器'
//                 }
//             },
//             {
//                 path: '/authority',
//                 component: AuthorityManagement,
//                 meta: {
//                     title: '权限管理'
//                 }
//             },
//             {
//                 path: '/log/server',
//                 component: ServerLog,
//                 meta: {
//                     title: '服务日志'
//                 }
//             },
//             {
//                 path: '/log/sql',
//                 component: SqlLog,
//                 meta: {
//                     title: 'SQL日志'
//                 }
//             },
//             {
//                 path: '/role',
//                 component: RoleList,
//                 meta: {
//                     title: '角色管理'
//                 }
//             },
//             {
//                 path: '/user/list',
//                 component: UserList,
//                 meta: {
//                     title: '用户管理'
//                 }
//             },
//             {
//                 path: '/user/token',
//                 component: TokenList,
//                 meta: {
//                     title: 'Token管理'
//                 }
//             },
//             {
//                 path: '/user/client',
//                 component: ClientList,
//                 meta: {
//                     title: '客户端管理'
//                 }
//             },
//             {
//                 path: '/department',
//                 component: DepartmentList,
//                 meta: {
//                     title: '部门管理'
//                 }
//             }
//         ]
//     },
//     {
//         path: '/login',
//         name: 'login',
//         component: Login,
//         meta: {
//             title: '登录'
//         }
//     }
// ]

const router = createRouter({
    history: createWebHistory(import.meta.env.VITE_BASE_URL),
    routes: routes
});

router.beforeEach(async (to, from, next) => {
    console.log({to,from})
    // if (to.name !== 'login') {
    //     if (!accessToken()) {
    //         next({name: 'login'});
    //         return;
    //     }
    // } else {
    //     if (accessToken()) {
    //         await apis.logout();
    //         removeToken();
    //     }
    // }
    next();
});

export default router;
