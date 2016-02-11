/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

/**
 *
 * @author doyenm
 */
public class Canvas extends JPanel {

    private int xBall;
    private int yBall;

    private int xLeftRacket;
    private int yLeftRacket;

    private int xRightRacket;
    private int yRightRacket;

    private Random random;

    public Canvas(int x, int y) {
        this.xBall = 225;
        this.yBall = 225;
        this.xLeftRacket = 0;
        this.yLeftRacket = 200;
        this.xRightRacket = 460;
        this.yRightRacket = 200;
        this.random = new Random();
    }

    public int selectDirection() {
        float sign = random.nextFloat();
        if (sign < 0.5) {
            return -random.nextInt(10);
        } else {
            return random.nextInt(10);
        }
    }

    public void initMove() {
        int majX = selectDirection();
        int majY = selectDirection();
        this.xBall += majX;
        this.yBall += majY;
        repaint();
//        try {
//            Thread.sleep(40);
//        } catch (InterruptedException e) {
//        }
        long start = System.nanoTime();
        while ((System.nanoTime() - start) < 50000000) {
        }
        this.continueMove(majX, majY);
    }

//    public void continueMove(int majX, int majY) {
//        while (true) {
//            this.xBall += majX;
//            this.yBall += majY;
//            repaint();
//            try {
//                Thread.sleep(40);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void continueMove(int majX, int majY) {
        while (true) {
            this.xBall += majX;
            this.yBall += majY;
            repaint();
            long start = System.nanoTime();
            while ((System.nanoTime() - start) < 50000000) {
            }
        }
    }

    public void upLeft() {
        this.yLeftRacket -= 10;
        repaint();
    }

    public void downLeft() {
        this.yLeftRacket += 10;
        repaint();
    }

    public void upRight() {
        this.yRightRacket -= 10;
        repaint();
    }

    public void downRight() {
        this.yRightRacket += 10;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillOval(xBall, yBall, 50, 50);
        g.setColor(Color.ORANGE);
        g.fillRoundRect(this.xLeftRacket, this.yLeftRacket, 40, 100, 15, 15);
        g.setColor(Color.YELLOW);
        g.fillRoundRect(this.xRightRacket, this.yRightRacket, 40, 100, 15, 15);
    }
}

class Ball {

    public Ball() {

    }
}
