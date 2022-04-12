package io.gitee.openfeign;

import io.gitee.openfeign.named.NamedContextFactory;

public class FeignContext extends NamedContextFactory<FeignClientSpecification> {

    public FeignContext() {
        super(FeignClientsConfiguration.class, "feign", "feign.client.name");
    }

}
