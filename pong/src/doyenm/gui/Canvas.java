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

    // We randomly select an initial direction for the ball
    public int selectDirection() {
        float sign = random.nextFloat();
        if (sign < 0.5) {
            return -random.nextInt(3);
        } else {
            return random.nextInt(3);
        }
    }

    public boolean checkHitBoxRight(int majX, int majY) {
        return ((this.xBall + this.radius + majX >= this.xRightRacket)
                && (this.yBall + majY >= this.yRightRacket)
                && (this.yBall + majY <= (this.yRightRacket + this.heightRect)));
    }

    public boolean checkHitBoxLeft(int majX, int majY) {
        return ((this.xBall + majX <= (this.xLeftRacket + this.widthRect))
                && (this.yBall + majY >= this.yLeftRacket)
                && (this.yBall + majY <= (this.yLeftRacket + this.heightRect)));
    }

    public boolean checkLoseRight() {
        return this.xBall >= this.width;
    }

    public boolean checkLoseLeft() {
        return this.xBall <= 0;
    }

    public boolean checkHitBoxUp(int majX, int majY) {
        return this.yBall + majY <= 0;
    }

    public boolean checkHitBoxDown(int majX, int majY) {
        return this.yBall + majY + this.radius >= this.height;
    }

//    public void initMove() {
//        int majX = selectDirection();
//        int majY = selectDirection();
//        this.xBall += majX;
//        this.yBall += majY;
//        repaint();
//        long start = System.nanoTime();
//        while ((System.nanoTime() - start) < 50000000) {
//        }
//        this.continueMove(majX, majY);
//    }
    public void initMove() {
        repaint();
        long start = System.nanoTime();
        while ((System.nanoTime() - start) < 10000000) {
        }
        this.continueMove(1, 2);
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
        boolean checkRight;
        boolean checkLeft;
        boolean checkDown;
        boolean checkUp;
        while (true) {
            // We check if we are inside the screen, i.e. if we do not loose.
            if (!checkLoseRight() && !checkLoseLeft()) {
                checkRight = checkHitBoxRight(majX, majY);
                checkLeft = checkHitBoxLeft(majX, majY);
                checkDown = checkHitBoxDown(majX, majY);
                checkUp = checkHitBoxUp(majX, majY);
                // If we have a collision 
                if (!checkRight && !checkLeft && !checkUp && !checkDown) {
                    System.out.println("1");
                    this.xBall += majX;
                    this.yBall += majY;
                    repaint();
                    long start = System.nanoTime();
                    while ((System.nanoTime() - start) < 10000000) {
                    }
                    
                    System.out.println("after the while");
                    // We only do the move we can to touch the right racket
                } else if (checkRight) {
                    this.xBall += (this.xRightRacket - this.xBall - this.radius);
                    repaint();
                    continueMove(-majX, majY);
                    // We only do the move we can to touch the left racket
                } else if (checkLeft) {
                    this.xBall -= (this.xBall - this.xLeftRacket - this.widthRect);
                    repaint();
                    continueMove(-majX, majY);
                    // We only do the move we can to touch the up 
                    // side of the screen
                } else if (checkUp) {
                    this.yBall = 0;
                    repaint();
                    continueMove(majX, -majY);
                    // We only do the move we can to touch the down 
                    // side of the screen
                } else if (checkDown) {
                    this.yBall = this.height - this.radius;
                    repaint();
                    continueMove(majX, -majY);
                } else {
                    System.out.println("Else");
                }
            } else {
                System.out.println("Loose");
                this.xBall = -200;
                this.yBall = -200;
                repaint();
                break;
            }
        }
    }

    public void upLeft() {
        if (this.yLeftRacket < this.keySensibility) {
            this.yLeftRacket = 0;
        } else {
            this.yLeftRacket -= this.keySensibility;
        }
        repaint();
    }

    public void downLeft() {
        if (this.yLeftRacket + this.heightRect
                > this.height - this.keySensibility) {
            this.yLeftRacket = this.height - this.heightRect;
        } else {
            this.yLeftRacket += this.keySensibility;
        }
        repaint();
    }

    public void upRight() {
        if (this.yRightRacket < this.keySensibility) {
            this.yRightRacket = 0;
        } else {
            this.yRightRacket -= this.keySensibility;
        }
        repaint();
    }

    public void downRight() {
        if (this.yRightRacket + this.heightRect
                > this.height - this.keySensibility) {
            this.yRightRacket = this.height - this.heightRect;
        } else {
            this.yRightRacket += this.keySensibility;
        }
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
