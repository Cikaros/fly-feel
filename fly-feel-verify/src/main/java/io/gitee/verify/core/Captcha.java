package io.gitee.verify.core;

import io.gitee.define.Constants;
import io.gitee.define.entity.Verify;
import io.gitee.verify.enums.VerifyType;
import io.gitee.verify.utils.Randoms;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;

/**
 * 验证码基类
 *
 * @author Cikaros
 * @date 2022/4/26
 */
public abstract class Captcha {

    /**
     * 宽度
     */
    private final int width;

    /**
     * 高度
     */
    private final int height;

    /**
     * 验证码类型
     */
    private final VerifyType type;

    /**
     * 字体类型
     */
    private final Font font;

    /**
     * 验证码长度
     */
    private final int length;

    /**
     * 获取类型
     */
    public abstract String contentType();

    /**
     * 输出流
     */
    public void write(OutputStream out, String code) throws IOException {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bi.getGraphics();
        try (OutputStream output = out) {
            this.graphics(graphics, code);
            graphics.dispose();
            ImageIO.write(bi, this.contentType(), output);
        }
    }

    protected final void graphics0(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(font.getSize() >> 5));
        // 填充背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        // 抗锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 绘制
     */
    public void graphics(Graphics g, String code) {
        Graphics2D graphics = (Graphics2D) g;
        this.graphics0(graphics);
        // 画干扰线
        this.drawBesselLine(Randoms.num(5), graphics);
        this.drawLine(Randoms.num(5), graphics);
        // 画干扰圆
        this.drawOval(Randoms.num(5, 10), graphics);
        // 绘制验证码
        this.drawChars(graphics, code);
    }

    /**
     * 给定范围获得随机颜色
     *
     * @param fc 0-255
     * @param bc 0-255
     * @return 随机颜色
     */
    protected Color color(int fc, int bc) {
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + Randoms.num(bc - fc);
        int g = fc + Randoms.num(bc - fc);
        int b = fc + Randoms.num(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 获取随机常用颜色
     *
     * @return 随机颜色
     */
    protected Color color() {
        int[] color = Constants.COLOR[Randoms.num(Constants.COLOR.length)];
        return new Color(color[0], color[1], color[2]);
    }

    /**
     * 随机画干扰线
     *
     * @param num 数量
     * @param g   Graphics2D
     */
    protected void drawLine(int num, Graphics2D g) {
        drawLine(num, color(), g);
    }

    /**
     * 随机画干扰线
     *
     * @param num   数量
     * @param color 颜色
     * @param g     Graphics2D
     */
    protected void drawLine(int num, Color color, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(color);
            int x1 = Randoms.num(-10, width - 10);
            int y1 = Randoms.num(5, height - 5);
            int x2 = Randoms.num(10, width + 10);
            int y2 = Randoms.num(2, height - 2);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 随机画干扰圆
     *
     * @param num 数量
     * @param g   Graphics2D
     */
    protected void drawOval(int num, Graphics2D g) {
        drawOval(num, color(), g);
    }

    /**
     * 随机画干扰圆
     *
     * @param num   数量
     * @param color 颜色
     * @param g     Graphics2D
     */
    protected void drawOval(int num, Color color, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(color);
            int w = 5 + Randoms.num(10);
            g.drawOval(Randoms.num(width - 25), Randoms.num(height - 15), w, w);
        }
    }

    /**
     * 随机画贝塞尔曲线
     *
     * @param num 数量
     * @param g   Graphics2D
     */
    protected void drawBesselLine(int num, Graphics2D g) {
        drawBesselLine(num, color(), g);
    }

    /**
     * 随机画贝塞尔曲线
     *
     * @param num   数量
     * @param color 颜色
     * @param g     Graphics2D
     */
    protected void drawBesselLine(int num, Color color, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(color);
            int x1 = 5, y1 = Randoms.num(5, height / 2);
            int x2 = width - 5, y2 = Randoms.num(height / 2, height - 5);
            int ctrlx = Randoms.num(width / 4, width / 4 * 3), ctrly = Randoms.num(5, height - 5);
            if (Randoms.num(2) == 0) {
                int ty = y1;
                y1 = y2;
                y2 = ty;
            }
            if (Randoms.num(2) == 0) { // 二阶贝塞尔曲线
                QuadCurve2D shape = new QuadCurve2D.Double();
                shape.setCurve(x1, y1, ctrlx, ctrly, x2, y2);
                g.draw(shape);
            } else { // 三阶贝塞尔曲线
                int ctrlx1 = Randoms.num(width / 4, width / 4 * 3), ctrly1 = Randoms.num(5, height - 5);
                CubicCurve2D shape = new CubicCurve2D.Double(x1, y1, ctrlx, ctrly, ctrlx1, ctrly1, x2, y2);
                g.draw(shape);
            }
        }
    }

    /**
     * 绘制验证码
     */
    protected void drawChars(Graphics2D graphics, String code) {
        // 画字符串
        graphics.setFont(font);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int fontWidth = width / code.length(); // 每一个字符所占的宽度
        int fontMarginLeft = (fontWidth - (int) fontMetrics.getStringBounds("8", graphics).getWidth()) / 2; // 字符的左右边距
        for (int i = 0; i < code.length(); i++) {
            graphics.setColor(this.color());
            int fontMarginTop = height - ((height - (int) fontMetrics.getStringBounds(String.valueOf(code.charAt(i)), graphics).getHeight()) >> 1); // 文字的纵坐标
            graphics.drawString(String.valueOf(code.charAt(i)), i * fontWidth + fontMarginLeft, fontMarginTop - 3);
        }
    }

    /**
     * 生成验证码
     */
    public String code() {
        switch (type) {
            case ONLY_DIGIT:
                return Randoms.alpha(Randoms.numMaxIndex, length);
            case ONLY_LITTER:
                return Randoms.alpha(Randoms.charMinIndex, Randoms.charMaxIndex, length);
            case ONLY_UPPER:
                return Randoms.alpha(Randoms.upperMinIndex, Randoms.upperMaxIndex, length);
            case ONLY_LOWER:
                return Randoms.alpha(Randoms.lowerMinIndex, Randoms.lowerMaxIndex, length);
            case ONLY_CHINESE:
                return Randoms.chinese(length);
            default:
                return Randoms.alpha(length);
        }
    }

    /**
     * 构建验证码对象
     */
    public Verify build() {
        String code = this.code();
        byte[] data = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            this.write(out, code);
            data = out.toByteArray();
        } catch (IOException ignored) {
        }
        return new Verify(width, height, code, this.contentType(), data);
    }

    protected Captcha() {
        this.width = 180;
        this.height = 64;
        this.length = 5;
        this.type = VerifyType.DEFAULT;
        this.font = Randoms.font(Font.PLAIN, 32F);
    }

    protected Captcha(int width, int height, int length, VerifyType type, Font font) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.type = type;
        this.font = font;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public VerifyType getType() {
        return type;
    }

    public Font getFont() {
        return font;
    }

    public int getLength() {
        return length;
    }

    public static class Builder {

        private final Class<? extends Captcha> clazz;

        /**
         * 宽度
         */
        private int width;

        /**
         * 高度
         */
        private int height;

        /**
         * 验证码类型
         */
        private VerifyType type;

        /**
         * 字体类型
         */
        private Font font;

        /**
         * 验证码长度
         */
        private int length;

        private Builder(Class<? extends Captcha> clazz) {
            this.clazz = clazz;
            this.width = 180;
            this.height = 64;
            this.length = 5;
            this.type = VerifyType.DEFAULT;
            this.font = Randoms.font(Font.PLAIN, 32F);
        }

        public static Builder create(Class<? extends Captcha> clazz) {
            return new Builder(clazz);
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setType(VerifyType type) {
            this.type = type;
            return this;
        }

        public Builder setFont(Font font) {
            this.font = font;
            return this;
        }

        public Builder setLength(int length) {
            this.length = length;
            return this;
        }

        public Captcha build() throws Exception {
            Constructor<? extends Captcha> constructor = clazz.getConstructor(int.class, int.class, int.class, VerifyType.class, Font.class);
            return constructor.newInstance(width, height, length, type, font);
        }


    }
}
