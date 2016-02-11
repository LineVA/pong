/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author doyenm
 */
public class KeyboardMoveListener implements KeyListener {

    private Canvas canvas;

    public KeyboardMoveListener(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // To move the left racket : Z and S
    // To move the right racket : VK_UP and VK_DOWN
    @Override
    public void keyPressed(KeyEvent e) {
        if ('z' == e.getKeyChar()) {
            canvas.upLeft();
        } else if (e.getKeyChar() == 's') {
            canvas.downLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_UP){
            canvas.upRight();
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            canvas.downRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
