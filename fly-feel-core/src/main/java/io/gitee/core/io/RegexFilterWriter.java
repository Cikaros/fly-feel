package io.gitee.core.io;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 * 字符过滤流
 *
 * @author Cikaros
 * @date 2022/4/27
 */
public class RegexFilterWriter extends FilterWriter {

    private final String regex;
    private final String replace;

    public RegexFilterWriter(String regex, Writer out) {
        super(out);
        this.regex = regex;
        this.replace = "";
    }

    public RegexFilterWriter(String regex, String replace, Writer out) {
        super(out);
        this.regex = regex;
        this.replace = replace;
    }

    @Override
    public void write(String str) throws IOException {
        out.write(str.replaceAll(regex, replace));
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        out.write(new String(cbuf).replaceAll(regex, replace));
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        this.write(str.substring(off, off + len));
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.write(Arrays.copyOfRange(cbuf, off, off + len));
    }

}
