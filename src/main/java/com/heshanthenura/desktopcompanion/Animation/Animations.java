package com.heshanthenura.desktopcompanion.Animation;

import com.heshanthenura.desktopcompanion.MainApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class Animations {
    public void move(Stage stage,ImageView img, double screenWidth, double screenHeight) {
        Thread moveThread = new Thread(() -> {
            while(true){
                NumNSide nns = randomDouble(stage.getX(),0,screenWidth-stage.getWidth());
                moveStage(stage, img,"assets/samurai/walk.gif", 0.05, nns.getD(), nns.getNum(), screenHeight - stage.getHeight());
                NumNSide nns2 = randomDouble(stage.getX(),3000,10000);
                stageIdle(stage,img,"assets/samurai/idle.gif", (long) nns2.getNum(),nns.getD());
            }
        });
        moveThread.start();
    }

    private void moveStage(Stage stage, ImageView img, String path, double speed,int direction, double endX, double endY)  {

        double l =Math.sqrt(Math.pow(stage.getX()-endX,2)+Math.pow(stage.getY()-endY,2));
        double v = speed;
        double duration = l/v;
        System.out.println("Duration: "+(l/v));
        double startTime = System.currentTimeMillis();
        double startX = stage.getX();
        double startY = stage.getY();
        try{
            String gif = GIFLoR( path, direction);
            System.out.println("GIF is: "+gif);
            Image newImg = new Image(MainApplication.class.getResource(gif).openStream());
            img.setImage(newImg);
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Error Path: "+path);
        }

        while (true) {
            double currentTime = System.currentTimeMillis();
            double elapsed = currentTime - startTime;
            double progress = elapsed / duration;

            if (progress >= 1.0) {
                progress = 1.0;
                break;
            }

            double deltaX = startX + ((endX - startX) * progress);
            double deltaY = startY + ((endY - startY) * progress);

            // Update stage position based on delta values
            stage.setX(deltaX);
            stage.setY(deltaY);

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void stageIdle(Stage stage,ImageView img,String path,long time,int direction)  {
        System.out.println("IDLE time"+time);
        try{
            Image newImg = new Image(MainApplication.class.getResource(GIFLoR( path, direction)).openStream());
            img.setImage(newImg);
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Error Path: "+path);
        }
        try{
            Thread.sleep( time);
        }catch (Exception e){
            System.out.println(e);
        }
    };
    private void stageAction(Stage stage,ImageView img,String path,long time)  {
        try{
            Image newImg = new Image(MainApplication.class.getResource(path).openStream());
            img.setImage(newImg);
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            Thread.sleep( time);
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Error Path: "+path);
        }
    };
    private NumNSide randomDouble(double initP,double min, double max){
        double randNum = Math.random() * (max - min) + min;
        NumNSide nns = new NumNSide();
        System.out.println("Random Number: "+randNum);
        if(randNum<initP){
            System.out.println("go left");
            nns.setD(-1);
            nns.setNum(randNum);

        }else{
            System.out.println("go right");
            nns.setD(1);
            nns.setNum(randNum);
        }
        return nns;
    }
    class NumNSide {
        double num;
        int d;

        public double getNum() {
            return num;
        }

        public void setNum(double num) {
            this.num = num;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }
    }
    private String GIFLoR(String path,int direction){
        int lastDotIndex = path.lastIndexOf('.');
        String afterLastDot = null;
        String beforeLastDot = null;
        if (lastDotIndex != -1) {
             beforeLastDot = path.substring(0, lastDotIndex); // Substring before the last dot
             afterLastDot = path.substring(lastDotIndex + 1); // Substring after the last dot
            if(direction<0){
                beforeLastDot= beforeLastDot+"L";

            }else{
                beforeLastDot= beforeLastDot+"R";
            }
            System.out.println("Before Last Dot: " + beforeLastDot);
            System.out.println("After Last Dot: " + afterLastDot);
        } else {
            System.out.println("No dot found in the string.");
        }
        afterLastDot="."+afterLastDot;
        System.out.println(beforeLastDot+afterLastDot);
        return beforeLastDot+afterLastDot;
    };
}
