package com.tsingby.game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel {
    //定义两个数组，分别存x,y坐标
    int[]x=new int[200];
    int[]y=new int[200];
    //🐍的长度
    int len;
    String direction;//方向
    //游戏暂停or开始
    boolean isStart=false;
    Timer timer;//计时器
    int foodx;
    int foody;
    //分数
    int grade;

    Music bgm=new Music("背景音乐");
    Music eat= new  Music("食物");
    Music die=new Music("死亡");
    //是否生存
    boolean isDie=false;//没有死，生存状态
    public void init(){
        Music.bgm();
        //初始化🐍的长度
        len=3;
        //🐍头坐标
        x[0]=175;
        y[0]=275;
        //第一节
        x[1]=150;
        y[1]=275;
        //第二节
        x[2]=125;
        y[2]=275;
        //初始化🐍头的方向
        direction="R";
        foodx=350;
        foody=375;
        //初始化分数
        grade=0;
    }//初始化
    public GamePanel(){
        init();
        //将焦点定位在当前操作的面板上
        this.setFocusable(true);
        //监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int Keycode=e.getKeyCode();
                if (Keycode==KeyEvent.VK_SPACE){
                    if(isDie==true){
                        //全部恢复到初始状态
                        isDie=false;
                        repaint();
                    }
                    else {
                        isStart=!isStart;
                        repaint();//重绘，再次调用paintComponent(Graphics g)
                    }
                }
                switch (Keycode){
                    case KeyEvent.VK_UP:direction="U";repaint();break;
                    case KeyEvent.VK_RIGHT:direction="R";repaint();break;
                    case KeyEvent.VK_LEFT:direction="L";repaint();break;
                    case KeyEvent.VK_DOWN:direction="D";repaint();break;
                }
            }
        });
        //对计时器进行初始化
        timer=new Timer(100, new ActionListener() {//事件监听，每100ms监听下是否发生了一个动作，把具体的动作放入actionPerformed
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStart&&isDie==false){
                    for (int i = len - 1; i > 0; i--) {
                        x[i]=x[i-1];y[i]=y[i-1];
                    }
                    //x[0]+=25;
                    changeDirection(direction,x,y);//改变方向

                    throughtwall(x,y);//穿墙
                    //是否吃到food；
                    eatFood(foodx,foody,x,y);
                    repaint();
                }
                //死亡判定
                for (int i = 1; i < len; i++) {
                    if(x[0]==x[i]&&y[0]==y[i]) isDie=true;
                }

            }});
        //定时器启动
        timer.start();
    }

    public void  eatFood(int foodx,int foody,int x[],int y[]){
        if (x[0]==foodx&&y[0]==foody){
            eat.eatPaly();  //吃食物的声音
            len++;
            Random random=new Random();
            this.foodx= ((int)(Math.random()*30)+1)*25;
            this.foody=(random.nextInt(26)+2)*25;
            grade+=10;
        }

    }
    public void throughtwall(int x[],int y[]){
        if(x[0]>750){
            x[0]=0;
        }
        if(x[0]<0){
            x[0]=750;
        }
        if(y[0]>725){
            y[0]=50;
        }
        if(y[0]<50){
            y[0]=725;
        }
    }
    public void changeDirection(String direction,int x[],int y[] ){
        if("R".equals(direction)){
            x[0]+=25;
        }
        if("U".equals(direction)){
            y[0]-=25;
        }if("L".equals(direction)){
            x[0]-=25;
        }if("D".equals(direction)){
            y[0]+=25;
        }
    }
    public void changeDirection(String direction,Graphics g){
        if("R".equals(direction)) {    //🐍头方向
            Images.headImg.paintIcon(this, g, x[0], y[0]);
        }
        if("L".equals(direction)) {    //🐍头方向
            Images.headImg.paintIcon(this, g, x[0], y[0]);
        }
        if("U".equals(direction)) {    //🐍头方向
            Images.headImg.paintIcon(this, g, x[0], y[0]);
        }
        if("D".equals(direction)) {    //🐍头方向
            Images.headImg.paintIcon(this, g, x[0], y[0]);
        }
        repaint();
    }
    public void drawScore(Graphics g){
        g.setColor(new Color(181, 187, 10));
        g.setFont(new Font("幼圆",Font.BOLD,16));
        g.drawString("SCORE:"+grade,15,35);
    }
    public void drawDie(Graphics g,boolean isDie){
        if(isDie==true){
            die.diePaly();       //死亡声音
            g.setColor(new Color(181, 187, 10));
            g.setFont(new Font("幼圆",Font.BOLD,40));

            g.drawString("GAME OVER!",250,200);
        }
    }
    public void drawFont(Graphics g,boolean isStart){
        if (isStart==false){
            //画文字
            g.setColor(new Color(181, 187, 10));
            g.setFont(new Font("幼圆",Font.BOLD,40));
            g.drawString("点击空格开始游戏",250,200);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(212, 219, 181));
        Images.headerImg.paintIcon(this,g,0,0);
        g.setColor(new Color(212, 219, 181)); //调节面板颜色
        g.fillRect(0,50,775,700);//画一个矩形
        //画蛇头
        changeDirection(direction,g);

        for (int i = 1; i < len; i++) {
            Images.bodyImg.paintIcon(this,g,x[i],y[i]);
        }

        drawFont(g,isStart);

        Images.foodImg.paintIcon(this,g,foodx,foody);//画食物

        //画分数
        drawScore(g);

        //画死亡界面
        drawDie(g,isDie);
    }


}
