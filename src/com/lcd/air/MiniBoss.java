package com.lcd.air;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MiniBoss implements Constants{
    private int x, y;
    private int size;
    private Image img;
    private Orientation orientation;
    private List<BulletBoss> bulletsMiniBoss = new ArrayList<>();
    private int wait;

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<BulletBoss> getBulletsMiniBoss() {
        return bulletsMiniBoss;
    }

    public void setBulletsMiniBoss(List<BulletBoss> bulletsMiniBoss) {
        this.bulletsMiniBoss = bulletsMiniBoss;
    }

    public void drawMiniBoss(Graphics2D graphics2D){
        graphics2D.drawImage(img,x, y,size,size,null);
        drawAllBulletBoss(graphics2D);

    }

    public void moveMiniBoss( long currentTime ) {
        if (currentTime % 2 != 0){
            return;
        }
        if (orientation == Orientation.REIGHT) {
            x ++;
            if (x >= WIDTH_FRAME + 100) {
                orientation = Orientation.LEFT;
            }
        } else {
            x--;
            y++;
            if (x < -100) {
                orientation = Orientation.REIGHT;
            }
        }

        if (orientation == Orientation.DOWN) {
            y++;
            if (y > HEIGHT_FRAME - 250) {
                orientation = Orientation.UP;
            }
        } else {
            y--;
            if (y < 0) {
                orientation = Orientation.DOWN;
            }
        }
    }

    public void fireBullet(long currentTime){
        if (currentTime % 250 != 0){
            return;
        }
        BulletBoss bulletMiniBoss = new BulletBoss();
        bulletMiniBoss.setWait(3);
                bulletMiniBoss.setX(x + size / 2);
                bulletMiniBoss.setY(y+size);
        bulletMiniBoss.setSize(8);
        bulletMiniBoss.setImage(new ImageIcon(Air.class.getResource("/image/bullet_yellow.png")).getImage());
        bulletMiniBoss.setOrientation(orientation);
        bulletsMiniBoss.add(bulletMiniBoss);
    }

    private void drawAllBulletBoss(Graphics2D g2d){
        for ( int i = 0; i < bulletsMiniBoss.size(); i++){
            bulletsMiniBoss.get(i).draw(g2d);
        }
    }

    public boolean moveAllBulletBoss( Air air) {
        for ( int i = 0; i < bulletsMiniBoss.size(); i++){
            boolean isInter = bulletsMiniBoss.get(i).move(air);
            if (isInter){
                return true;
            }
//            if ( bulletsMiniBoss.get(i).checkOutScreen()){
//                bulletsMiniBoss.remove(i);
//                i--;
//            }
        }
        return false;
    }
}
