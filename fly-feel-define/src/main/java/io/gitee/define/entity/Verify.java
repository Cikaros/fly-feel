package io.gitee.define.entity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

/**
 * 验证码对象
 *
 * @author Cikaros
 * @date 2022/4/2
 * @since v1.0
 */
public class Verify {

    private final String uuid;

    private final String code;

    private final String contentType;

    private final byte[] data;

    public Verify(String code, String contentType, byte[] data) {
        this.uuid = UUID.randomUUID().toString();
        this.code = code;
        this.contentType = contentType;
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCode() {
        return code;
    }

    public String getContentType() {
        return contentType;
    }

    public boolean isMatches(String code) {
        return this.code.equalsIgnoreCase(code);
    }

    public void write(OutputStream out) throws IOException {
        out.write(data);
    }

    public String base64() {
        return this.getContentType() + Base64.getEncoder().encodeToString(data);
    }

}
