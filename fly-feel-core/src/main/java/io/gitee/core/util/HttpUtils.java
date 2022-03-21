package io.gitee.core.util;

import javax.servlet.ServletRequest;
import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Http工具类
 *
 * @author Cikaros
 * @date 2021/12/19
 */
public class HttpUtils {

    /**
     * 获取Body数据并转换为byte数组
     *
     * @param request 请求对象
     * @return byte数组
     */
    public static byte[] getBodyByteArray(ServletRequest request) throws IOException {
        try (InputStream in = request.getInputStream();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            byte[] buff = new byte[2048];
            int len;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            out.flush();
            return out.toByteArray();
        }
    }

    /**
     * 获取Body数据并转换为字符串
     *
     * @param request 请求对象
     * @return Body字符串
     */
    public static String getBodyString(ServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
