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

    private final int width;

    private final int height;

    private final String uuid;

    private final String code;

    private final String contentType;

    private final byte[] data;

    public Verify(int width, int height, String code, String contentType, byte[] data) {
        this.width = width;
        this.height = height;
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
        return String.format("data:image/%s;base64,%s", contentType, Base64.getEncoder().encodeToString(data));
    }

    @Override
    public String toString() {
        return "Verify{" +
                "width=" + width +
                ", height=" + height +
                ", uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
