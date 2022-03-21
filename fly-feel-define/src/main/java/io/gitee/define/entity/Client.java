package io.gitee.define.entity;

import io.gitee.core.entity.model.BaseModel;

import java.util.Collection;

/**
 * 三方客户端表
 *
 * @author Cikaros
 * @date 2022/3/17
 * @since v1.0
 */
public class Client extends BaseModel {
    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 重定向url
     */
    private String redirectUri;

    /**
     * token有效时长
     */
    private Integer accessValiditySeconds;

    /**
     * 刷新token有效时长
     */
    private Integer refreshValiditySeconds;

    /**
     * 数据范围
     */
    private Collection<String> scopes;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public Integer getAccessValiditySeconds() {
        return accessValiditySeconds;
    }

    public void setAccessValiditySeconds(Integer accessValiditySeconds) {
        this.accessValiditySeconds = accessValiditySeconds;
    }

    public Integer getRefreshValiditySeconds() {
        return refreshValiditySeconds;
    }

    public void setRefreshValiditySeconds(Integer refreshValiditySeconds) {
        this.refreshValiditySeconds = refreshValiditySeconds;
    }

    public Collection<String> getScopes() {
        return scopes;
    }

    public void setScopes(Collection<String> scopes) {
        this.scopes = scopes;
    }
}