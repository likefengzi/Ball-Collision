package sample;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball implements Runnable{
    private Pane pane;
    private Color color;
    private Random random=new Random();
    private final double PI=3.1415926;
    private double angel;
    private int x;
    private int y;
    private final int speed=10;
    private final int border=5;
    private final int correct=20;

    public Ball(Pane pane) {
        this.pane=pane;
    }

    @Override
    public void run() {
        Circle circle=create();
        angel=PI*2*random.nextDouble();
        Circle finalCircle = circle;
        //javafx 使用Platform.runLater修改UI
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pane.getChildren().add(finalCircle);
            }
        });
        while (circle!=null){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                circle.setVisible(false);
                circle=null;
                break;
            }
            //角度范围
            if (angel>PI){
                angel=angel-2*PI;
            }
            if (angel<-PI){
                angel=angel+2*PI;
            }
            //水平或者竖直时修正角度
            if (angel<PI+PI/correct&&angel>PI-PI/correct){
                if (angel<PI+PI/correct){
                    angel=PI+PI/correct;
                }
                else {
                    angel=PI-PI/correct;
                }
                x= (int) (x+speed*Math.cos(angel));
                y= (int) (y-speed*Math.sin(angel));
            }
            else if (angel<PI/2+PI/correct&&angel>PI/2-PI/correct){

                if (angel<PI/2+PI/correct){
                    angel=PI/2+PI/correct;
                }
                else {
                    angel=PI/2-PI/correct;
                }
                x= (int) (x+speed*Math.cos(angel));
                y= (int) (y-speed*Math.sin(angel));
            }
            else if (angel<0+PI/correct&&angel>0-PI/correct){
                if (angel<0+PI/correct){
                    angel=0+PI/correct;
                }
                else {
                    angel=0-PI/correct;
                }

                x= (int) (x+speed*Math.cos(angel));
                y= (int) (y-speed*Math.sin(angel));
            }
            else if (angel<-PI/2+PI/correct&&angel>-PI/2-PI/correct){
                if (angel<-PI/2+PI/correct){
                    angel=-PI/2+PI/correct;
                }
                else {
                    angel=-PI/2-PI/correct;
                }

                x= (int) (x+speed*Math.cos(angel));
                y= (int) (y-speed*Math.sin(angel));
            }
            else if (angel<-PI+PI/correct&&angel>-PI-PI/correct){
                if (angel<-PI+PI/correct){
                    angel=-PI+PI/correct;
                }
                else {
                    angel=-PI-PI/correct;
                }
                x= (int) (x+speed*Math.cos(angel));
                y= (int) (y-speed*Math.sin(angel));
            }

            x= (int) circle.getCenterX();
            y= (int) circle.getCenterY();
            //边界碰撞
            if (x<=25){
                x=25+border;
                if (angel<=0){
                    angel=-PI-angel;
                }
                else {
                    angel=PI-angel;
                }
            }
            else if (x>=1375){
                x=1375-border;
                if (angel<=0){
                    angel=-PI-angel;
                }
                else {
                    angel=PI-angel;
                }
            }
            else if (y<=25){
                y=25+border;
                if (angel<=PI/2){
                    angel=-angel;
                }
                else {
                    angel=-angel;
                }
            }
            else if(y>=675){
                y=675-border;
                if (angel<=-PI/2){
                    angel=-angel;
                }
                else {
                    angel=-angel;
                }
            }
            x= (int) (x+speed*Math.cos(angel));
            y= (int) (y-speed*Math.sin(angel));
            Circle finalCircle1 = circle;
            //javafx 使用Platform.runLater修改UI
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    finalCircle1.setCenterX(x);
                    finalCircle1.setCenterY(y);
                }
            });
        }
    }
    //创建圆
    private Circle create(){
        //随机颜色
        color= Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        Circle circle=new Circle();
        circle.setFill(color);
        circle.setCenterX(700);
        circle.setCenterY(350);
        circle.setRadius(25);
       return circle;
    }
}
