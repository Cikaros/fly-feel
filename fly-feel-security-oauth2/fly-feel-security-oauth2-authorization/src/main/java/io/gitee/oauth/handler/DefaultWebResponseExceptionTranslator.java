package io.gitee.oauth.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * 自定义Oauth异常处理
 *
 * @author Cikaros
 * @date 2022/3/21
 * @since v1.0
 */
public class DefaultWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    public DefaultWebResponseExceptionTranslator() {
    }

    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        Throwable[] causeChain = this.throwableAnalyzer.determineCauseChain(e);
        OAuth2Exception ex = (OAuth2Exception) this.throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);
        if (ex != null) {
            throw ex;
        } else {
            AuthenticationException ex1 = (AuthenticationException) this.throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
            if (ex1 != null) {
                throw ex1;
            } else {
                AccessDeniedException ex2 = (AccessDeniedException) this.throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
                if (ex2 instanceof AccessDeniedException) {
                    throw ex2;
                } else {
                    HttpRequestMethodNotSupportedException ex3 = (HttpRequestMethodNotSupportedException) this.throwableAnalyzer.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
                    throw ex3;
                }
            }
        }
    }
}
