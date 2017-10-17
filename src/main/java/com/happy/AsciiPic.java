package com.happy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/17/11:30
 * @Desc:
 **/
public class AsciiPic {

    /**
     * @param path 图片路径
     */
    public static void createAsciiPic(final String path) {
        final String base = "@#&$%*o!;.";// 字符串由复杂到简单
        try {
            final BufferedImage image = ImageIO.read(new File(path));
            for (int y = 0; y < image.getHeight(); y += 2) {
                for (int x = 0; x < image.getWidth(); x++) {
                    final int pixel = image.getRGB(x, y);//获取点的rgb
                    final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                    final float gray = 0.299f * r + 0.578f * g + 0.114f * b;//获取灰度
                    final int index = Math.round(gray * (base.length() + 1) / 255);
                    System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
                }
                System.out.println();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * test
     *
     * @param args
     */
    public static void main(final String[] args) {
        AsciiPic.createAsciiPic("F:\\elder1.jpg");
    }
}
