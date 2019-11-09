package com.lcd.air;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Bullet {
    private int x;
    private int y;
    private Image image;
    private int size;
    private int wait;
    private Orientation orientation;

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, size, size, null);
    }

    public boolean move(List<MiniBoss> miniBosses, Boss boss) {
        y -= 1;
        int n = interact(miniBosses);
        if (n>0){
            miniBosses.remove(n);
            return true;
        }

        return false;
    }

//    public void move1(long cureentTime) {
//        if (cureentTime % wait != 0) {
//            return ;
//        }
//        setX(x+1);
//        y -= 1;
//    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public boolean checkOutScreen() {
        switch (orientation) {
            case LEFT:
                return x < -size;
            case UP:
                return y < -size;
            case REIGHT:
                return x > 400;
            default:
                return y > 680;
        }
    }

    public int interact(List<MiniBoss> miniBosses){
        Rectangle reBullet = new Rectangle(x, y, size, size);
        for ( int i = 0; i < miniBosses.size(); i++){
            MiniBoss f = miniBosses.get(i);
            Rectangle reMiniBoss = new Rectangle(f.getX(),
                    f.getY(), f.getSize(), f.getSize());
            if (reBullet.intersects(reMiniBoss) == true){
                return i;
            }
        }
//        Rectangle reBoss = new Rectangle(x,y,size,size);
//        if (reBullet.intersects(reBoss)==true){
//            return 1;
//        }
        return -1;
    }
}
