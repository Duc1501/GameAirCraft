package com.lcd.air;

import javax.swing.*;
import java.util.Random;

public class GUI extends JFrame implements Constants {
    public GUI(){
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setTitle("Air");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int idBg= new Random().nextInt(7);
        add(new GamePanel(idBg));
        add(new Header(idBg));
    }
}
