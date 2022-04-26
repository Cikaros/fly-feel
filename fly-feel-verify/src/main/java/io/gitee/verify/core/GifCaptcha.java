package io.gitee.verify.core;

import io.gitee.verify.enums.VerifyType;
import io.gitee.verify.utils.GifEncoder;
import io.gitee.verify.utils.Randoms;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * GIF验证码对象
 *
 * @author Cikaros
 * @date 2022/4/26
 */
public class GifCaptcha extends Captcha {
    @Override
    public String contentType() {
        return "gif";
    }

    @Override
    public void write(OutputStream out, String code) throws IOException {
        try (OutputStream output = out) {
            // 开始画gif每一帧
            GifEncoder gifEncoder = new GifEncoder();
            gifEncoder.setQuality(180);
            gifEncoder.setDelay(100);
            gifEncoder.setRepeat(0);
            gifEncoder.start(output);
            for (int i = 0; i < super.getLength(); i++) {
                BufferedImage frame = new BufferedImage(super.getWidth(), super.getHeight(), BufferedImage.TYPE_INT_RGB);
                this.graphics(frame.getGraphics(), code);
                gifEncoder.addFrame(frame);
                frame.flush();
            }
            gifEncoder.finish();
        }
    }

    @Override
    public void graphics(Graphics g, String code) {
        Graphics2D graphics = (Graphics2D) g;
        // 随机生成每个文字的颜色
        Color[] colors = new Color[super.getLength()];
        for (int i = 0; i < super.getLength(); i++) {
            colors[i] = super.color();
        }
        // 随机生成贝塞尔曲线参数
        int x1 = 5, y1 = Randoms.num(5, super.getHeight() / 2);
        int x2 = super.getWidth() - 5, y2 = Randoms.num(super.getHeight() / 2, super.getHeight() - 5);
        int ctrlx = Randoms.num(super.getWidth() / 4, super.getWidth() / 4 * 3);
        int ctrly = Randoms.num(5, super.getHeight() - 5);
        if (Randoms.num(2) == 0) {
            int ty = y1;
            y1 = y2;
            y2 = ty;
        }
        int ctrlx1 = Randoms.num(super.getWidth() / 4, super.getWidth() / 4 * 3);
        int ctrly1 = Randoms.num(5, super.getHeight() - 5);
        int[][] bessel = new int[][]{{x1, y1}, {ctrlx, ctrly}, {ctrlx1, ctrly1}, {x2, y2}};
        // 填充背景颜色
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, super.getWidth(), super.getHeight());
        // 抗锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 画干扰圆圈
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f * Randoms.num(10))); // 设置透明度
        drawOval(2, graphics);
        // 画干扰线
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); // 设置透明度
        graphics.setStroke(new BasicStroke(1.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        graphics.setColor(colors[0]);
        CubicCurve2D shape = new CubicCurve2D.Double(bessel[0][0], bessel[0][1], bessel[1][0], bessel[1][1],
                bessel[2][0], bessel[2][1], bessel[3][0], bessel[3][1]);
        graphics.draw(shape);
        // 画验证码
        graphics.setFont(getFont());
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int fW = super.getWidth() / code.length(); // 每一个字符所占的宽度
        int fSp = (fW - (int) fontMetrics.getStringBounds("W", graphics).getWidth()) / 2; // 字符的左右边距
        for (int i = 0; i < code.length(); i++) {
            // 设置透明度
            AlphaComposite ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha(i * 2));
            graphics.setComposite(ac3);
            graphics.setColor(colors[i]);
            int fY = super.getHeight()
                    - ((super.getHeight() - (int) fontMetrics.getStringBounds(String.valueOf(code.charAt(i)), graphics).getHeight()) >> 1); // 文字的纵坐标
            graphics.drawString(String.valueOf(code.charAt(i)), i * fW + fSp + 3, fY - 3);
        }
    }

    /**
     * 获取透明度,从0到1,自动计算步长
     *
     * @return 透明度
     */
    private float getAlpha(int num) {
        float r = (float) 1 / (super.getLength() - 1);
        float s = super.getLength() * r;
        return num >= super.getLength() ? (num * r - s) : num * r;
    }

    public GifCaptcha() {
    }

    public GifCaptcha(int width, int height, int length, VerifyType type, Font font) {
        super(width, height, length, type, font);
    }
}
