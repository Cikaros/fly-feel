package io.gitee.verify.core;

import io.gitee.core.io.RegexFilterWriter;
import io.gitee.verify.enums.VerifyType;
import org.apache.batik.anim.dom.SVG12DOMImplementation;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;

/**
 * SVG验证码对象
 *
 * @author Cikaros
 * @date 2022/4/26
 */
public class SvgCaptcha extends Captcha {

    private static final String SVG_URI = "http://www.w3.org/2000/svg";

    private static final String REGEX = "\\s{2}\\s*";

    @Override
    public String contentType() {
        return "svg";
    }

    @Override
    public void write(OutputStream out, String code) throws IOException {
        try (Writer output = new RegexFilterWriter(REGEX, " ", new BufferedWriter(new OutputStreamWriter(out)))) {
            DOMImplementation domImpl = SVG12DOMImplementation.getDOMImplementation();
            //创建SVG文件
            Document doc = domImpl.createDocument(SVG_URI, this.contentType(), null);
            SVGGraphics2D graphics = new SVGGraphics2D(doc);
            this.graphics(graphics, code);
            //输出SVG文件
            graphics.stream(output);
        }
    }

    public SvgCaptcha() {
    }

    public SvgCaptcha(int width, int height, int length, VerifyType type, Font font) {
        super(width, height, length, type, font);
    }
}
