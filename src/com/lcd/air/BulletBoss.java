package com.lcd.air;

import java.awt.*;
import java.util.List;

public class BulletBoss {
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

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
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

    public boolean move(Air air) {
//        if (cureentTime % wait != 0) {
//            return ;
//        }
        y += 1;
        int n = interact(air);
        if (n>0){
            return true;
        }

        return false;
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

    public int interact(Air air){
        Rectangle reBulletBoss = new Rectangle(x, y, size, size);
            Rectangle reAir = new Rectangle(air.getX(),air.getY(),air.getSize(),air.getSize());
            if (reBulletBoss.intersects(reAir) == true){
                return 1;
            }

        return -1;
    }
}
