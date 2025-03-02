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

import feign.QueryMap;
import io.gitee.openfeign.annotation.QueryMapParameterProcessor;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Spring MVC equivalent of OpenFeign's {@link QueryMap} parameter annotation.
 *
 * @author Aram Peres
 * @see QueryMap
 * @see QueryMapParameterProcessor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface SpringQueryMap {

    /**
     * @return alias for {@link #encoded()}.
     * @see QueryMap#encoded()
     */
    @AliasFor("encoded")
    boolean value() default false;

    /**
     * @return Specifies whether parameter names and values are already encoded.
     * @see QueryMap#encoded()
     */
    @AliasFor("value")
    boolean encoded() default false;

}
