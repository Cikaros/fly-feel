package io.gitee.openfeign;

import feign.Feign;
import feign.Target;

interface Targeter {

    <T> T target(FeignClientFactoryBean factory,
                 Feign.Builder feign,
                 FeignContext context,
                 Target.HardCodedTarget<T> target);

}
