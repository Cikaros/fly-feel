package io.gitee.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * IO工具类
 *
 * @author Cikaros
 * @date 2022/3/19
 * @since v1.0
 */
public class IOUtils {

    public static String toString(InputStream in, Charset charset) {
        StringBuilder sb = new StringBuilder();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ignored) {
        }
        return sb.toString();
    }

    public static String toString(InputStream in) {
        return toString(in, Charset.defaultCharset());
    }
}
