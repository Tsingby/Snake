package com.tsingby.game;

import javax.swing.*;
import java.awt.*;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame jf=new JFrame();//创建窗体
        jf.setTitle("贪吃蛇-------------马青波");
      // int scHeight= Toolkit.getDefaultToolkit().getScreenSize().height; // 获取屏幕高度
       // int scwidth=Toolkit.getDefaultToolkit().getScreenSize().width; // 获取屏幕宽度
        jf.setBounds(700,20,800,800);
        jf.setResizable(false);//窗口大小不可调节
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口时，程序也自动关闭
        jf.add(new GamePanel());

        jf.setVisible(true);
    }
}
