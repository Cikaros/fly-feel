package io.gitee.openfeign;

import feign.Logger;

public interface FeignLoggerFactory {

    /**
     * Factory method to provide a {@link Logger} for a given {@link Class}.
     *
     * @param type the {@link Class} for which a {@link Logger} instance is to be created
     * @return a {@link Logger} instance
     */
    Logger create(Class<?> type);

}