package com.lcd.air;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Constants, KeyListener, Runnable {
    private AirManager airManager;
    private Image image;
    private long cureentTime = 0;
    private boolean isDie = false;
    private boolean isDie1 = false;

    public GamePanel(int idBg) {
        setSize(WIDTH_FRAME, HEIGHT_FRAME - HEIGHT_HEADER);
        setLocation(0, HEIGHT_HEADER);
        setBackground(Color.GRAY);
        airManager = new AirManager();
        image = new ImageIcon(GamePanel.class.getResource("/image/nen" + idBg + ".png")).getImage();
        setFocusable(true);
        addKeyListener(this);
        initThread();
//        initThreadBoss();
//        initThreadMiniBoss();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, WIDTH_FRAME, HEIGHT_FRAME - HEIGHT_HEADER,
                0, image.getHeight(null) * HEIGHT_HEADER / HEIGHT_FRAME,
                image.getWidth(null), image.getHeight(null),
                null);
        airManager.drawAll(graphics2D);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        airManager.press(code);
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        airManager.pressReleased(code);
        repaint();
    }

    private void initThread() {
        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        while (true) {
            airManager.stepThread(cureentTime);
            isDie = airManager.stepThreadBoss(cureentTime);
            if (isDie) {
                int result = JOptionPane.showConfirmDialog(
                        this, "Do you want replay?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    airManager.initGame();
                }

            }
            isDie1 = airManager.stepThreadMiniBoss(cureentTime);
            if (isDie1) {
                int result1 = JOptionPane.showConfirmDialog(
                        this, "Do you want replay?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (result1 == JOptionPane.YES_OPTION) {
                    airManager.initGame();
                }

            }

                airManager.stepThreadCloud(cureentTime);
                repaint();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cureentTime += 1;
            }

        }

}
