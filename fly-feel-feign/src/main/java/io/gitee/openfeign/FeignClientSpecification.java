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

import io.gitee.openfeign.named.NamedContextFactory;

import java.util.Arrays;
import java.util.Objects;


/**
 * 对应每个client的上下文
 * FeignClient中的configuration (容器扫描到FeignClient是解析处configuration 创建对应的上下文 FeignContext)
 *
 * @author Dave Syer
 * @author Gregor Zurowski
 */
class FeignClientSpecification implements NamedContextFactory.Specification {

    private String name;

    private Class<?>[] configuration;

    FeignClientSpecification() {
    }

    FeignClientSpecification(String name, Class<?>[] configuration) {
        this.name = name;
        this.configuration = configuration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?>[] getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(Class<?>[] configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeignClientSpecification that = (FeignClientSpecification) o;
        return Objects.equals(this.name, that.name)
                && Arrays.equals(this.configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, Arrays.hashCode(this.configuration));
    }

    @Override
    public String toString() {
        return "FeignClientSpecification{" + "name='" +
                this.name + "', " + "configuration=" +
                Arrays.toString(this.configuration) + "}";
    }

}
