package com.tsingby.game;
import javax.swing.*;
import java.net.URL;

public class Images{
    public static   URL bodyURL=Images.class.getResource("/images/body.png");
    //将图片封装为对象
    public static ImageIcon bodyImg=new ImageIcon(bodyURL);

    public static  URL headURL=Images.class.getResource("/images/head.png");
    public static ImageIcon headImg=new ImageIcon(headURL);

    public static URL foodURL=Images.class.getResource("/images/food.png");
    public static  ImageIcon foodImg=new ImageIcon(foodURL);

    public static URL headerURL=Images.class.getResource("/images/header.png");
    public static  ImageIcon headerImg=new ImageIcon(headerURL);

}
