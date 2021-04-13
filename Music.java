package com.tsingby.game;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;

public class Music extends JFrame {
   File f;
   static URL url;
    Music(){}
    Music(String ph) {     //"D:\\javatest\\demo\\snake\\src\\music\\背景音乐.wav"
        String path = "D:\\javatest\\demo\\snake\\src\\music\\" + ph + ".wav";
        try {
            f = new File(path);
            url= f.toURI().toURL();//解析地址

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        } finally {

            System.out.println("Finally");
        }
    }
public static void bgm(){

    AudioClip aau= null;
    try {
        aau = Applet.newAudioClip((new File("D:\\javatest\\demo\\snake\\src\\music\\背景音乐.wav")).toURI().toURL());
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    aau.loop();  //循环播放
}
    public static void eatPaly() {
        AudioClip aau= null;
        try {
            aau = Applet.newAudioClip((new File("D:\\javatest\\demo\\snake\\src\\music\\食物音乐.wav")).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        aau.play();  //循环播放
    }
    public static void diePaly() {
        AudioClip aau= null;
        try {
            aau = Applet.newAudioClip((new File("D:\\javatest\\demo\\snake\\src\\music\\死亡音乐.wav")).toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        aau.play();  //循环播放
    }

    public static void main(String[] args) {
        Music.bgm();
    }

}