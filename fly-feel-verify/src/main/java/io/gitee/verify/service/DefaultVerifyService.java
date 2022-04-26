package io.gitee.verify.service;

import io.gitee.core.service.BaseService;
import io.gitee.define.entity.Verify;
import io.gitee.define.service.ICacheService;
import io.gitee.define.service.IVerifyService;
import io.gitee.verify.core.Captcha;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 默认验证码业务实现
 *
 * @author Cikaros
 * @date 2022/4/2
 * @since v1.0
 */
@Service
@ConditionalOnBean(IVerifyService.class)
public class DefaultVerifyService extends BaseService implements IVerifyService {

    private final ICacheService cacheService;

    private final Captcha captcha;

    public DefaultVerifyService(ICacheService cacheService, Captcha captcha) {
        this.cacheService = cacheService;
        this.captcha = captcha;
    }

    @Override
    public Verify getInstance() {
        Verify verify = captcha.build();
        cacheService.cache(verify.getUuid(), verify);
        return verify;
    }

    @Override
    public boolean isMatches(String uuid, String code) {
        Verify verify = cacheService.get(uuid, Verify.class);
        return Objects.nonNull(verify) && verify.isMatches(code);
    }
}
