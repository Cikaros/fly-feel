package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

/**
 * 账户表
 * 
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class Account extends BaseModel {
    /**
    * 账号
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 昵称
    */
    private String nickname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}