import axios from "axios";
import qs from 'qs';
import router from "../router";
import {ElMessage} from 'element-plus';
import {setToken, accessToken, tokenType, removeToken} from './token';

const instance = axios.create({
    baseURL: import.meta.env.VITE_BASE_API,
    timeout: import.meta.env.VITE_REQUEST_TIMEOUT,
    headers: {
        'Content-type': 'application/json;charset=utf-8'
    }
});

const exclude_url = [
    '/oauth/token?grant_type=password',
    '/verify'
];

// 请求拦截器
instance.interceptors.request.use(config => {
    // 除了登录接口 其他接口请求都需要在请求头上添加 token
    if (!exclude_url.includes(config.url)) {
        if (accessToken()) {
            config.headers['Authorization'] = `${tokenType()} ${accessToken()}`;
        }
    }
    return config;
}, error => {
    Promise.reject(error).then((err) => {
        ElMessage.error(err.message);
    });
});

// 响应拦截器
instance.interceptors.response.use(response => {
    return response;
}, error => {
    if (error.response) {
        switch (error.response.status) {
            case 400:
                ElMessage.warning(error.response.message);
                break;
            case 401:
                removeToken();
                router.replace({path: '/login'}).then(() => {
                });
                ElMessage.warning(error.response.message);
                break;
            case 403: // token 认证通过 但是没有权限
                ElMessage.warning(error.response.data.message);
                break;
            case 404:
                ElMessage.warning(error.response.data.message);
                break;
            // 其他错误，直接抛出错误提示
            default:
                ElMessage.error(error.response.data.message);
        }
    } else {
        ElMessage.error("网络异常！");
    }
    return Promise.reject(error);
});

// get 方式
export function get(url, params) {
    return new Promise((resolve, reject) => {
        instance.get(url, {
            params
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    });
}

// 传参格式为 form-data
export function post(url, params) {
    return new Promise((resolve, reject) => {
        instance({
            method: 'POST',
            url,
            headers: {
                'Content-type': 'application/x-www-form-urlencoded;charset=utf-8'
            },
            data: qs.stringify(params)
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        });
    });
}

// 传参格式为 json
export function postObj(url, params) {
    return new Promise((resolve, reject) => {
        instance.post(url, params).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err);
        })
    });
}

// 认证登录
export function auth(url, params, auth) {
    return new Promise((resolve, reject) => {
        instance({
            method: 'POST',
            url,
            headers: {
                'Content-type': 'application/x-www-form-urlencoded;charset=utf-8',
                'Authorization': `Basic ${auth}`
            },
            data: qs.stringify(params)
        }).then(res => {
            setToken(res.data.access_token, res.data.token_type);
            resolve(res);
        }).catch(err => {
            reject(err);
        });
    });
}
