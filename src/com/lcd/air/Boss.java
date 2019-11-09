package com.lcd.air;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Boss implements Constants {
    private int x, y;
    private int size;
    private Image img;
    private int wait;
    private Orientation orientation;
    private List<BulletBoss> bulletBosses = new ArrayList<>();

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

    public List<BulletBoss> getBulletBosses() {
        return bulletBosses;
    }

    public void setBulletBosses(List<BulletBoss> bulletBosses) {
        this.bulletBosses = bulletBosses;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(img, x, y, size, size, null);
        drawAllBulletBoss(graphics2D);
    }

    public void move(long time) {
        if (time % wait != 0) {
            return;
        }
        if (orientation == Orientation.REIGHT) {
            x++;
            if ( x >= WIDTH_FRAME - size){
                orientation = Orientation.LEFT;
            }
        } else {
            x--;
            if ( x <0 ){
                orientation = Orientation.REIGHT;
            }
        }
    }

    public void fireBulletBoss(long currentTime){
        if (currentTime % 160 != 0){
            return;
        }
        BulletBoss bulletBoss1 = new BulletBoss();
        bulletBoss1.setWait(1);
        BulletBoss bulletBoss2 = new BulletBoss();
        bulletBoss2.setWait(2);
        BulletBoss bulletBoss3 = new BulletBoss();
        bulletBoss3.setWait(2);

        switch (orientation){
            case LEFT:
                bulletBoss1.setX(x + size / 2);
                bulletBoss1.setY(y+size);
                bulletBoss2.setX(x + size / 13);
                bulletBoss2.setY(y+size-15);
                bulletBoss3.setX(x + 13*size / 15);
                bulletBoss3.setY(y+size-15);

                break;
            case UP:
                bulletBoss1.setX(x + size / 2);
                bulletBoss1.setY(y +size);
                bulletBoss2.setX(x + size / 13);
                bulletBoss2.setY(y+size-15);
                bulletBoss3.setX(x + 13*size / 15);
                bulletBoss3.setY(y+size-15);
                break;
            case REIGHT:
                bulletBoss1.setX(x + size / 2);
                bulletBoss1.setY(y +size);
                bulletBoss2.setX(x + size / 13);
                bulletBoss2.setY(y+size-15);
                bulletBoss3.setX(x + 13*size / 15);
                bulletBoss3.setY(y+size-15);
                break;
            default:
                bulletBoss1.setX(x + size / 2);
                bulletBoss1.setY(y +size);
                bulletBoss2.setX(x + size / 13);
                bulletBoss2.setY(y+size-15);
                bulletBoss3.setX(x + 13*size / 15);
                bulletBoss3.setY(y+size-15);
                break;
        }
        bulletBoss1.setSize(10);
        bulletBoss1.setImage(new ImageIcon(Air.class.getResource("/image/bullet9.png")).getImage());
        bulletBoss1.setOrientation(orientation);
        bulletBosses.add(bulletBoss1);

        bulletBoss2.setSize(10);
        bulletBoss2.setImage(new ImageIcon(Air.class.getResource("/image/bullet6.png")).getImage());
        bulletBoss2.setOrientation(orientation);
        bulletBosses.add(bulletBoss2);

        bulletBoss3.setSize(10);
        bulletBoss3.setImage(new ImageIcon(Air.class.getResource("/image/bullet6.png")).getImage());
        bulletBoss3.setOrientation(orientation);
        bulletBosses.add(bulletBoss3);
    }

    private void drawAllBulletBoss(Graphics2D g2d){
        for ( int i = 0; i < bulletBosses.size(); i++){
            bulletBosses.get(i).draw(g2d);
        }
    }

    public boolean moveAllBulletBoss(Air air) {
        for ( int i = 0; i < bulletBosses.size(); i++){
            if(bulletBosses.get(i).move(air)){
                return true;
            }
            if ( bulletBosses.get(i).checkOutScreen()){
                bulletBosses.remove(i);
                i--;
            }
        }
        return false;
    }
}
