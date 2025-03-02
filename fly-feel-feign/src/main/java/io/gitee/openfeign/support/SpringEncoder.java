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

package io.gitee.openfeign.support;

import feign.Request;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Objects;

import static io.gitee.openfeign.support.FeignUtils.getHeaders;
import static io.gitee.openfeign.support.FeignUtils.getHttpHeaders;

/**
 * @author Spencer Gibb
 * @author Scien Jus
 */
public class SpringEncoder implements Encoder {

    private static final Log log = LogFactory.getLog(SpringEncoder.class);

    private final SpringFormEncoder springFormEncoder = new SpringFormEncoder();

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    public SpringEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Override
    public void encode(Object requestBody, Type bodyType, RequestTemplate request)
            throws EncodeException {
        // template.body(conversionService.convert(object, String.class));
        if (requestBody != null) {
            Class<?> requestType = requestBody.getClass();
            Collection<String> contentTypes = request.headers().get("Content-Type");

            MediaType requestContentType = null;
            if (contentTypes != null && !contentTypes.isEmpty()) {
                String type = contentTypes.iterator().next();
                requestContentType = MediaType.valueOf(type);
            }

            if (bodyType != null && bodyType.equals(MultipartFile.class)) {
                if (Objects.equals(requestContentType, MediaType.MULTIPART_FORM_DATA)) {
                    this.springFormEncoder.encode(requestBody, bodyType, request);
                    return;
                } else {
                    String message = "Content-Type \"" + MediaType.MULTIPART_FORM_DATA
                            + "\" not set for request body of type "
                            + requestBody.getClass().getSimpleName();
                    throw new EncodeException(message);
                }
            }

            for (HttpMessageConverter<?> messageConverter : this.messageConverters.getObject().getConverters()) {

                if (messageConverter.canWrite(requestType, requestContentType)) {
                    if (log.isDebugEnabled()) {
                        if (requestContentType != null) {
                            log.debug("Writing [" + requestBody + "] as \"" + requestContentType + "\" using [" + messageConverter + "]");
                        } else {
                            log.debug("Writing [" + requestBody + "] using [" + messageConverter + "]");
                        }
                    }

                    FeignOutputMessage outputMessage = new FeignOutputMessage(request);
                    try {
                        @SuppressWarnings("unchecked")
                        HttpMessageConverter<Object> copy = (HttpMessageConverter<Object>) messageConverter;
                        copy.write(requestBody, requestContentType, outputMessage);
                    } catch (IOException ex) {
                        throw new EncodeException("Error converting request body", ex);
                    }
                    // clear headers
                    request.headers(null);
                    // converters can modify headers, so update the request
                    // with the modified headers
                    request.headers(getHeaders(outputMessage.getHeaders()));

                    // do not use charset for binary data and protobuf
                    Charset charset;
                    if (messageConverter instanceof ByteArrayHttpMessageConverter) {
                        charset = null;
                    } else if (messageConverter instanceof ProtobufHttpMessageConverter
                            && ProtobufHttpMessageConverter.PROTOBUF.isCompatibleWith(
                            outputMessage.getHeaders().getContentType())) {
                        charset = null;
                    } else {
                        charset = StandardCharsets.UTF_8;
                    }
                    request.body(Request.Body.encoded(
                            outputMessage.getOutputStream().toByteArray(), charset));
                    return;
                }
            }
            String message = "Could not write request: no suitable HttpMessageConverter "
                    + "found for request type [" + requestType.getName() + "]";
            if (requestContentType != null) {
                message += " and content type [" + requestContentType + "]";
            }
            throw new EncodeException(message);
        }
    }

    private final class FeignOutputMessage implements HttpOutputMessage {

        private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        private final HttpHeaders httpHeaders;

        private FeignOutputMessage(RequestTemplate request) {
            this.httpHeaders = getHttpHeaders(request.headers());
        }

        @Override
        public OutputStream getBody() throws IOException {
            return this.outputStream;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.httpHeaders;
        }

        public ByteArrayOutputStream getOutputStream() {
            return this.outputStream;
        }

    }

}
