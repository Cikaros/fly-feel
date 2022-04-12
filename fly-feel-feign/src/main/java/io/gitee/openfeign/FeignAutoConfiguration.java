/*
 * Copyright 2013-2019 the original author or authors.
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

package io.gitee.openfeign;

import feign.Client;
import feign.Feign;
import feign.httpclient.ApacheHttpClient;
import feign.okhttp.OkHttpClient;
import io.gitee.openfeign.httpclient.ApacheHttpClientConnectionManagerFactory;
import io.gitee.openfeign.httpclient.ApacheHttpClientFactory;
import io.gitee.openfeign.httpclient.OkHttpClientConnectionPoolFactory;
import io.gitee.openfeign.httpclient.OkHttpClientFactory;
import io.gitee.openfeign.support.FeignClientProperties;
import io.gitee.openfeign.support.FeignHttpClientProperties;
import io.gitee.openfeign.template.TemplateClient;
import okhttp3.ConnectionPool;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Spencer Gibb
 * @author Julien Roy
 */
@Configuration
@ConditionalOnClass(Feign.class)
@EnableConfigurationProperties({FeignClientProperties.class, FeignHttpClientProperties.class})
public class FeignAutoConfiguration {

    @Autowired(required = false)
    private List<FeignClientSpecification> configurations = new ArrayList<>();


    @Bean
    public FeignContext feignContext() {
        FeignContext context = new FeignContext();
        context.setConfigurations(this.configurations);
        return context;
    }

    /**
     * 默认使用RestTemplate
     *
     * @author Cikaros
     */
    @Bean
    @ConditionalOnMissingBean(Client.class)
    public Client defaultClient(@Autowired(required = false) RestTemplate restTemplate) {
        return new TemplateClient(restTemplate);
    }

    @Configuration
    @ConditionalOnClass(name = "feign.hystrix.HystrixFeign")
    protected static class HystrixFeignTargeterConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public Targeter feignTargeter() {
            return new HystrixTargeter();
        }

    }

    @Configuration
    @ConditionalOnMissingClass("feign.hystrix.HystrixFeign")
    protected static class DefaultFeignTargeterConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public Targeter feignTargeter() {
            return new DefaultTargeter();
        }

    }

    // the following configuration is for alternate feign clients if
    // ribbon is not on the class path.
    // see corresponding configurations in FeignRibbonClientAutoConfiguration
    // for load balanced ribbon clients.
    @Configuration
    @ConditionalOnClass(ApacheHttpClient.class)
    @ConditionalOnMissingClass("com.netflix.loadbalancer.ILoadBalancer")
    @ConditionalOnMissingBean(CloseableHttpClient.class)
    @ConditionalOnProperty(value = "feign.httpclient.enabled", matchIfMissing = true)
    protected static class HttpClientFeignConfiguration {

        private final Timer connectionManagerTimer = new Timer(
                "FeignApacheHttpClientConfiguration.connectionManagerTimer", true);

        @Autowired(required = false)
        private RegistryBuilder registryBuilder;

        private CloseableHttpClient httpClient;

        @Bean
        @ConditionalOnMissingBean(HttpClientConnectionManager.class)
        public HttpClientConnectionManager connectionManager(
                ApacheHttpClientConnectionManagerFactory connectionManagerFactory,
                FeignHttpClientProperties httpClientProperties) {
            final HttpClientConnectionManager connectionManager = connectionManagerFactory
                    .newConnectionManager(httpClientProperties.isDisableSslValidation(),
                            httpClientProperties.getMaxConnections(),
                            httpClientProperties.getMaxConnectionsPerRoute(),
                            httpClientProperties.getTimeToLive(),
                            httpClientProperties.getTimeToLiveUnit(),
                            this.registryBuilder);
            this.connectionManagerTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    connectionManager.closeExpiredConnections();
                }
            }, 30000, httpClientProperties.getConnectionTimerRepeat());
            return connectionManager;
        }

        @Bean
        public CloseableHttpClient httpClient(ApacheHttpClientFactory httpClientFactory,
                                              HttpClientConnectionManager httpClientConnectionManager,
                                              FeignHttpClientProperties httpClientProperties) {
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setConnectTimeout(httpClientProperties.getConnectionTimeout())
                    .setRedirectsEnabled(httpClientProperties.isFollowRedirects())
                    .build();
            this.httpClient = httpClientFactory.createBuilder()
                    .setConnectionManager(httpClientConnectionManager)
                    .setDefaultRequestConfig(defaultRequestConfig).build();
            return this.httpClient;
        }

        @Bean
        @ConditionalOnMissingBean(Client.class)
        public Client feignClient(HttpClient httpClient) {
            return new ApacheHttpClient(httpClient);
        }

        @PreDestroy
        public void destroy() throws Exception {
            this.connectionManagerTimer.cancel();
            if (this.httpClient != null) {
                this.httpClient.close();
            }
        }

    }

    @Configuration
    @ConditionalOnClass(OkHttpClient.class)
    @ConditionalOnMissingClass("com.netflix.loadbalancer.ILoadBalancer")
    @ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
    @ConditionalOnProperty("feign.okhttp.enabled")
    protected static class OkHttpFeignConfiguration {

        private okhttp3.OkHttpClient okHttpClient;

        @Bean
        @ConditionalOnMissingBean(ConnectionPool.class)
        public ConnectionPool httpClientConnectionPool(
                FeignHttpClientProperties httpClientProperties,
                OkHttpClientConnectionPoolFactory connectionPoolFactory) {
            int maxTotalConnections = httpClientProperties.getMaxConnections();
            long timeToLive = httpClientProperties.getTimeToLive();
            TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
            return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
        }

        @Bean
        public okhttp3.OkHttpClient client(OkHttpClientFactory httpClientFactory,
                                           ConnectionPool connectionPool,
                                           FeignHttpClientProperties httpClientProperties) {
            boolean followRedirects = httpClientProperties.isFollowRedirects();
            int connectTimeout = httpClientProperties.getConnectionTimeout();
            boolean disableSslValidation = httpClientProperties.isDisableSslValidation();
            this.okHttpClient = httpClientFactory.createBuilder(disableSslValidation)
                    .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                    .followRedirects(followRedirects).connectionPool(connectionPool)
                    .build();
            return this.okHttpClient;
        }

        @PreDestroy
        public void destroy() {
            if (this.okHttpClient != null) {
                this.okHttpClient.dispatcher().executorService().shutdown();
                this.okHttpClient.connectionPool().evictAll();
            }
        }

        @Bean
        @ConditionalOnMissingBean(Client.class)
        public Client feignClient(okhttp3.OkHttpClient client) {
            return new OkHttpClient(client);
        }

    }

}
