package com.lcd.air;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel implements Constants {
    private Image imgBg;
    private JButton btnPause;
    public Header(int idBg){
        imgBg = new ImageIcon(GamePanel.class.getResource("/image/nen"+ idBg +".png")).getImage();
        setSize(WIDTH_FRAME, HEIGHT_HEADER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(imgBg,0,0,WIDTH_FRAME,HEIGHT_HEADER, 0,0,
                imgBg.getWidth(null),imgBg.getHeight(null)*HEIGHT_HEADER/HEIGHT_FRAME,null);


    }
}
