/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gitee.openfeign.httpclient;

import okhttp3.ConnectionPool;

import java.util.concurrent.TimeUnit;

/**
 * Creates {@link ConnectionPool}s for {@link okhttp3.OkHttpClient}s.
 *
 * @author Ryan Baxter
 */
public interface OkHttpClientConnectionPoolFactory {

    /**
     * Creates a new {@link ConnectionPool}.
     *
     * @param maxIdleConnections Number of max idle connections to allow.
     * @param keepAliveDuration  Amount of time to keep connections alive.
     * @param timeUnit           The time unit for the keep-alive duration.
     * @return A new {@link ConnectionPool}.
     */
    ConnectionPool create(int maxIdleConnections, long keepAliveDuration,
                          TimeUnit timeUnit);

}
