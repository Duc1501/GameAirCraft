package com.lcd.air;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cloud {
    private int x, y;
    private int size;
    private Image img;
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

    public void drawCloud(Graphics2D graphics2D){
        graphics2D.drawImage(img, x, y, size,size,null);
    }

    public void moveCloud(long currentTime){
        if (currentTime % 5 != 0){
            return;
        }
        y += 1;
    }
}
