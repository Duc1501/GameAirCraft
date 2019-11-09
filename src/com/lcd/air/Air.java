package com.lcd.air;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEConfig;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Air implements Constants{
    private int x, y;
    private int size;
    private Image img;
    private Orientation orientation;
    private List<Bullet> bullets = new ArrayList<>();
    private int wait;

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

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

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(img, x, y, size, size, null);
        drawAllBullet(graphics2D);
    }

    public void move( ){
        switch (orientation){
            case LEFT:

                if(x<0){
                    x-=0;
                }else {
                    x-=1;
                }
                break;
            case REIGHT:
                if(x> WIDTH_FRAME -size){
                    x+=0;
                }else {
                    x+=1;
                }
                break;
            case UP:
                if (y< 0){
                    y-=0;
                }else {
                    y-=1;
                }
                break;
            case DOWN:
                if(y > HEIGHT_FRAME-125 ){
                    y+=0;
                }else {
                    y+=1;
                }
                break;
        }
    }

    public void fireBullet(long currentTime){
        if (currentTime % 25 != 0){
            return;
        }
        Bullet bullet = new Bullet();
        bullet.setWait(1);
        Bullet bullet1 = new Bullet();
        bullet1.setWait(1);
        Bullet bullet2 = new Bullet();
        bullet2.setWait(1);
        Bullet bullet3 = new Bullet();
        bullet3.setWait(1);
        switch (orientation){
            case LEFT:
                bullet.setX(x + size / 3);
                bullet.setY(y-2);
                bullet2.setX(x + size / 2);
                bullet2.setY(y +5);
                bullet3.setX(x + size / 6);
                bullet3.setY(y+ 5);
                break;
            case UP:
                bullet.setX(x + size / 3);
                bullet.setY(y-2);
                bullet2.setX(x + size / 2);
                bullet2.setY(y);
                bullet3.setX(x + size / 6);
                bullet3.setY(y+ 5);
                break;
            case REIGHT:
                bullet.setX(x + size / 3);
                bullet.setY(y-2);
                bullet2.setX(x + size / 2);
                bullet2.setY(y+ 5);
                bullet3.setX(x + size / 6);
                bullet3.setY(y+ 5);
                break;
            default:
                bullet.setX(x + size / 3);
                bullet.setY(y-2);
                bullet2.setX(x + size / 2);
                bullet2.setY(y+ 5);
                bullet3.setX(x + size / 6);
                bullet3.setY(y+ 5);
                break;
        }
        bullet.setSize(20);
        bullet.setImage(new ImageIcon(Air.class.getResource("/image/bullet2.png")).getImage());
        bullet.setOrientation(orientation);
        bullets.add(bullet);

        bullet2.setSize(20);
        bullet2.setImage(new ImageIcon(Air.class.getResource("/image/bullet2.png")).getImage());
        bullet2.setOrientation(orientation);
        bullets.add(bullet2);

        bullet3.setSize(20);
        bullet3.setImage(new ImageIcon(Air.class.getResource("/image/bullet2.png")).getImage());
        bullet3.setOrientation(orientation);
        bullets.add(bullet3);
    }
    private void drawAllBullet(Graphics2D g2d){
        for ( int i = 0; i < bullets.size(); i++){
            bullets.get(i).draw(g2d);
        }
    }

    public void moveAllBullet( List<MiniBoss> miniBosses, Boss boss) {
        for ( int i = 0; i < bullets.size(); i++){
            bullets.get(i).move(miniBosses,boss);
            if ( bullets.get(i).checkOutScreen()){
                bullets.remove(i);
                i--;
            }
        }
    }

}
