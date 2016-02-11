/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.testAnimations;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLayeredPane;

/**
 *
 * @author doyenm
 */
public class Animation extends JFrame {

    public Animation() {
        super("Animation");
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        int size;

        Canvas canvas = new Canvas(0, 0, Color.GREEN);
        JLayeredPane layeredPane = new JLayeredPane();
        canvas.setBounds(0, 0, 500, 500);
        layeredPane.add(canvas);
        addKeyListener(new KeyboardListener(canvas));

        setContentPane(layeredPane);
        setSize(500, 585);
        setVisible(true);
//        go(canvas);
    }

}

class KeyboardListener implements KeyListener {

    Canvas pane;

    public KeyboardListener(Canvas pane) {
        this.pane = pane;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
        System.out.println(e.getKeyChar());
        pane.go();
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

class Canvas extends JPanel {

    public int x;
    public int y;
    public Color color;

    public Canvas(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void go() {
        //for(int i = -50; i < pan.getWidth(); i++){
        x = x + 50;
        y = y + 50;
        x = x;
        y = y;
        repaint();
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //}
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        g.fillOval(x, y, 50, 50);
    }

}
