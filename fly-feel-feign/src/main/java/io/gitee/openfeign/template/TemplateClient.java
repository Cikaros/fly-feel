package io.gitee.openfeign.template;

import feign.Client;
import feign.Request;
import feign.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;


/**
 * 默认接口调用实现
 *
 * @author Cikaros
 * @date 2022/4/12
 */
public class TemplateClient implements Client {

    private final RestTemplate template;


    public TemplateClient(RestTemplate template) {
        if (Objects.isNull(template)) {
            template = new RestTemplate();
        }
        this.template = template;
    }

    public Response execute(Request request, Request.Options options) {
        RequestEntity<?> requestEntity = toRequestEntity(request);
        ResponseEntity<byte[]> exchange = template.exchange(requestEntity, byte[].class);
        return toResponse(request, exchange);
    }

    HttpMethod toHttpMethod(Request request) {
        return HttpMethod.resolve(request.httpMethod().name());
    }

    RequestEntity<?> toRequestEntity(Request request) {
        HttpMethod method = toHttpMethod(request);
        return RequestEntity.method(method, URI.create(request.url()))
                .headers(toHeaders(request))
                .body(request.body());

    }

    HttpHeaders toHeaders(Request request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, Collection<String>> headers = request.headers();
        headers.forEach((key, values) -> httpHeaders.addAll(key, new ArrayList<>(values)));
        return httpHeaders;
    }

    Response toResponse(Request request, ResponseEntity<byte[]> response) {
        return Response.builder()
                .status(response.getStatusCodeValue())
                .request(request)
                .requestTemplate(request.requestTemplate())
                .headers(new HashMap<>(response.getHeaders()))
                .body(response.getBody())
                .build();
    }

}
