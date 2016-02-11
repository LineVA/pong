/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.gui;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author doyenm
 */
public class PlayGui extends JFrame {

    public PlayGui() {
        super("Pong");
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);

        Canvas canvas = new Canvas(100, 100);
        JLayeredPane layeredPane = new JLayeredPane();
        canvas.setBounds(0, 0, 500, 500);
        layeredPane.add(canvas);
        addKeyListener(new KeyboardMoveListener(canvas));
        
        setContentPane(layeredPane);
        setSize(500, 600);
        setVisible(true);
        canvas.initMove();

    }

    
}
