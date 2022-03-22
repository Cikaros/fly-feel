package io.gitee.define.entity;

import io.gitee.define.enums.GrantType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义ClientDetails
 *
 * @author Cikaros
 * @date 2022/3/19
 * @since v1.0
 */
public class SecurityClient extends Client implements ClientDetails {

    private static final Collection<String> approveScopes = Collections.singleton("all");

    private Collection<Server> servers;

    private Collection<ClientGrantType> grantTypes;

    private Collection<SecurityRole> roles;

    public Collection<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<SecurityRole> roles) {
        this.roles = roles;
    }

    public void setServers(Collection<Server> servers) {
        this.servers = servers;
    }

    public Collection<Server> getServers() {
        return servers;
    }

    public Collection<ClientGrantType> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(Collection<ClientGrantType> grantTypes) {
        this.grantTypes = grantTypes;
    }

    @Override
    public Set<String> getResourceIds() {
        return servers.stream().map(Server::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public boolean isScoped() {
        return Objects.nonNull(super.getScopes()) && !super.getScopes().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return new HashSet<>(super.getScopes());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return grantTypes.stream()
                .map(ClientGrantType::getAuthorizedGrantType)
                .map(GrantType::name)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Collections.singleton(super.getRedirectUri());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return roles.stream().flatMap(role -> role.getAuthorities().stream()).collect(Collectors.toSet());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return super.getAccessValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return super.getRefreshValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return approveScopes.contains(s);
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }
}
