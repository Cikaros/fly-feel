import Cookies from "js-cookie";

// 保存 token
export function setToken(access_token, token_type) {
    Cookies.set('access_token', access_token);
    Cookies.set('token_type', token_type);
}

// 获取 token
export function accessToken() {
    return Cookies.get('access_token');
}

// 获取 token type
export function tokenType() {
    return Cookies.get('token_type');
}

// 移出 token
export function removeToken() {
    Cookies.remove('access_token');
    Cookies.remove('token_type');
}
