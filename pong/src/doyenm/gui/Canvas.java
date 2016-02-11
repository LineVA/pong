/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author doyenm
 */
public class Canvas extends JPanel {

    private int xBall;
    private int yBall;
    private final int radius = 25;

    private final int widthRect = 40;
    private final int heightRect = 100;
    private final int roundRect = 15;

    private final int xLeftRacket;
    private int yLeftRacket;
    private final int xRightRacket;
    private int yRightRacket;

    private final int width = 500;
    private final int height = 500;

    private final int keySensibility = 10;

    private final Random random;

    public Canvas(int x, int y) {
        this.xBall = this.width / 2 - radius / 2;
        this.yBall = this.height / 2 - radius / 2;
        this.xLeftRacket = 0;
        this.yLeftRacket = this.height / 2 - this.heightRect / 2;
        this.xRightRacket = this.width - this.widthRect;
        this.yRightRacket = this.height / 2 - this.heightRect / 2;
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

    public boolean checkHitBox(int majX, int majY) {
        return ((this.xBall + this.radius + majX >= this.xRightRacket)
                && (this.yBall >= this.yRightRacket)
                && (this.yBall <= (this.yRightRacket + this.heightRect)));
    }

    public void initMove() {
        int majX = selectDirection();
        int majY = selectDirection();
        this.xBall += majX;
        this.yBall += majY;
        repaint();
        long start = System.nanoTime();
        while ((System.nanoTime() - start) < 50000000) {
        }
        this.continueMove(majX, majY);
    }
    
//    public void initMove() {
//        repaint();
//        long start = System.nanoTime();
//        while ((System.nanoTime() - start) < 10000000) {
//        }
//        this.continueMove(5, 2);
//    }

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
            // If we can make an elementary move without hit a racket
            if (!checkHitBox(majX, majY)) {
                this.xBall += majX;
                this.yBall += majY;
                repaint();
                // if (!checkHitBox()) {
                long start = System.nanoTime();
                while ((System.nanoTime() - start) < 50000000) {
                }
                // We only do the move we can to touch the racket
            } else {
                this.xBall += (this.xRightRacket - this.xBall - this.radius);
                repaint();
                break;
            }
        }
    }

    public void upLeft() {
        this.yLeftRacket -= this.keySensibility;
        repaint();
    }

    public void downLeft() {
        this.yLeftRacket += this.keySensibility;
        repaint();
    }

    public void upRight() {
        this.yRightRacket -= this.keySensibility;
        repaint();
    }

    public void downRight() {
        this.yRightRacket += this.keySensibility;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillOval(xBall, yBall, this.radius, this.radius);
        g.setColor(Color.ORANGE);
        g.fillRoundRect(this.xLeftRacket, this.yLeftRacket, this.widthRect,
                this.heightRect, this.roundRect, this.roundRect);
        g.setColor(Color.YELLOW);
        g.fillRoundRect(this.xRightRacket, this.yRightRacket, this.widthRect,
                this.heightRect, this.roundRect, this.roundRect);
    }
}
