package com.lcd.air;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirManager implements Constants {
    private Air air;
    private Boss boss;
    private MiniBoss miniB;
    private List<MiniBoss> miniBoss;
    private List<Bullet> bulletList;
    private boolean isLeft;
    private boolean isUp;
    private boolean isRight;
    private boolean isDown;
    private long myTime;
    private Cloud cloud;
    private List<Cloud> clouds;
    private int timeCreateCloud = 100;
    private int timeCreateMiniBoss =100;

    public AirManager() {
        initGame();
    }

    public void initGame(){
        clouds = new ArrayList<>();
        initsCloud();
        initsAir();
        initsBoss();
        miniBoss = new ArrayList<>();
        initsMiniBoss();
    }
    private void initsCloud() {
        clouds.add(creadCloud());
    }

    private Cloud creadCloud() {
        Random rd = new Random();
        Cloud cloud = new Cloud();
        int sizeCloud = rd.nextInt(1)+7;
        cloud.setX(rd.nextInt(WIDTH_FRAME));
        cloud.setY(-10);
        cloud.setSize(sizeCloud);
        int indexImage = rd.nextInt(5);
        cloud.setImg(new ImageIcon(AirManager.class.getResource("/image/"+"/rock"+ indexImage + ".png" )).getImage());
        return cloud;
    }
    private void initsMiniBoss() {
        miniBoss.add(creatMiniBoss());

    }

    private MiniBoss creatMiniBoss() {
        Random rd = new Random();
        MiniBoss miniBoss = new MiniBoss();
        int size = 30;
        miniBoss.setX(rd.nextInt(WIDTH_FRAME -size));
        miniBoss.setY(-size);
        miniBoss.setSize(size);
        int indexImage = rd.nextInt(2);
        miniBoss.setImg(new ImageIcon(AirManager.class.getResource("/image/" + indexImage + ".png")).getImage());
        return miniBoss;
    }

    private void initsAir() {
        air = new Air();
        air.setX(WIDTH_FRAME / 2 - 30);
        air.setY(550);
        air.setSize(60);
        air.setOrientation(Orientation.DOWN);
        air.setImg(new ImageIcon(AirManager.class.getResource("/image/Char.png")).getImage());
    }

    public void initsBoss() {
        boss = new Boss();
        boss.setWait(7);
        boss.setX(WIDTH_FRAME / 2 - 50);
        boss.setY(10);
        boss.setSize(100);
        boss.setImg(new ImageIcon(AirManager.class.getResource("/image/Boss1.png")).getImage());
        boss.setOrientation(Orientation.DOWN);
    }


    public void drawAll(Graphics2D graphics2D) {
        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).drawCloud(graphics2D);
        }
        air.draw(graphics2D);
        boss.draw(graphics2D);
        for (int i = 0; i < miniBoss.size(); i++) {
            miniBoss.get(i).drawMiniBoss(graphics2D);
        }


    }

    public void press(int code) {
        switch (code) {
            case KeyEvent.VK_LEFT:
                isLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = true;
                break;
            case KeyEvent.VK_UP:
                isUp = true;
                break;
            case KeyEvent.VK_DOWN:
                isDown = true;
                break;
//            case KeyEvent.VK_SPACE:
//                isSpace = true;
//                break;

        }
    }

    public void pressReleased(int code) {
        switch (code) {
            case KeyEvent.VK_LEFT:
                isLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                isRight = false;
                break;
            case KeyEvent.VK_UP:
                isUp = false;
                break;
            case KeyEvent.VK_DOWN:
                isDown = false;
                break;
//            case KeyEvent.VK_SPACE:
//                isSpace = false;
//                break;
        }
    }

    public void moveAir() {
        if (isLeft) {
            air.setOrientation(Orientation.LEFT);
            air.move();
        } else {
            if (isUp) {
                air.setOrientation(Orientation.UP);
                air.move();
            } else {
                if (isRight) {
                    air.setOrientation(Orientation.REIGHT);
                    air.move();
                } else {
                    if (isDown) {
                        air.setOrientation(Orientation.DOWN);
                        air.move();
                    }
                }
            }
        }
    }

    public void stepThread(long cureentTime) {
        myTime += 1;
        air.moveAllBullet(miniBoss,boss);
        moveAir();
        air.fireBullet(cureentTime);
    }



    public void stepThreadCloud(long cureentTime) {
        for (int i = 0; i <clouds.size(); i++) {
            clouds.get(i).moveCloud(cureentTime);
            if (clouds.get(i).getY() > HEIGHT_FRAME) {
                clouds.remove(i);
                i--;
            }
        }
            if (cureentTime % timeCreateCloud == 0){
                clouds.add(creadCloud());
            }


    }


    public boolean stepThreadBoss(long currentTime) {
        myTime +=1;
        boolean isInter = boss.moveAllBulletBoss(air);
        if (isInter){
            return true;
        }
        boss.move(currentTime);
        boss.fireBulletBoss(currentTime);
        return false;
    }

    public boolean stepThreadMiniBoss(long cureentTime) {
        myTime +=1;
        for (int i = 0; i <miniBoss.size(); i++) {
            miniBoss.get(i).moveMiniBoss(cureentTime);
            boolean isInter =miniBoss.get(i).moveAllBulletBoss(air);
            if (isInter){
                return true;
            }
            miniBoss.get(i).fireBullet(cureentTime);
        }
        if (cureentTime % timeCreateMiniBoss == 0){
            miniBoss.add(creatMiniBoss());
        }
        return false;

    }
}

