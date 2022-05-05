import {get, post, postObj, auth} from "./http";

const apis = {
    verify: p => get('/verify', p),//获取验证码
    login: p => auth('/oauth/token?grant_type=password', p, import.meta.env.VITE_BASIC), // 登录
    logout: p => post('/logout', p), // 退出
    getEnumTable: p => postObj('/enums/' + p), // 获取枚举列表
}

export default apis;
