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
import javax.swing.JLayeredPane;

/**
 *
 * @author doyenm
 */
public class Animation extends JFrame{
    public Animation(){
            super("Animation");
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        int size;
      
        Canvas canvas = new Canvas(100, 100);
         JLayeredPane layeredPane = new JLayeredPane();
        canvas.setBounds(0, 0, 500, 500);
        layeredPane.add(canvas);
        
         setContentPane(layeredPane);
        setSize(500, 585);
        setVisible(true);
        go(canvas);
    }
    
    private void go(Canvas pan){
    for(int i = -50; i < pan.getWidth(); i++){
      int x = pan.x, y = pan.y;
      x++;
      y++;
      pan.x = x;
      pan.y = y;
      pan.repaint();  
      try {
        Thread.sleep(300);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }       
}

class Canvas extends JPanel{
    
    public int x;
    public int y;
    
    public Canvas(int x, int y){
        this.x = x;
        this.y = y;
    }
    
     @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.fillOval(x, y, 50, 50);
    }
    
    
}
