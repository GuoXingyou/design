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


    private static final char[] defaultCharset = {'@','#','&','$','*','o','!',';','.'};//复杂到简单

    private char[] charset;//字符画素材集

    private String imgString = "";//转化后的字符串

    //使用指定字符集
    public AsciiPic(char[] charset){
        this.charset = charset;
    }

    //使用默认字符集构造
    public AsciiPic(){
        this.charset = defaultCharset;
    }

    public String getImgString(){
        return imgString;
    }



    public AsciiPic mapConvert(String path){
        File imageFile = new File(path);
        if(!imageFile.exists()){ //当读取的文件不存在时，结束程序
            System.out.println("File is not exists!");
            System.exit(1);
        }

        StringBuffer sb = new StringBuffer();

        try {
            final BufferedImage image = ImageIO.read(imageFile);
            for (int y = 0; y < image.getHeight(); y += 2) {
                for (int x = 0; x < image.getWidth(); x++) {
                    final int pixel = image.getRGB(x, y);//获取点的rgb
                    final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                    final float gray = 0.299f * r + 0.578f * g + 0.114f * b;//获取灰度
                    final int index = Math.round(gray * (charset.length + 1) / 255);//根据灰度选取字符index
                    String str = index >= charset.length ? " " : String.valueOf(charset[index]);

                    sb.append(str);
                }
                sb.append("\r\n");
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }

        imgString = sb.toString();
        return this;
    }


    /**
     * test
     *
     * @param args
     */
    public static void main(final String[] args) {

        char[] str = {'@','#','!',',','.'};
        AsciiPic asciiPic = new AsciiPic(str);


        asciiPic.mapConvert("F:\\elder1.jpg");

        System.out.println(asciiPic.imgString);


    }
}
