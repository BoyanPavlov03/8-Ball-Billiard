package com.elsys;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    WhiteBall a;

    public KeyInput(WhiteBall a){
        this.a = a;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_A)
            a.isMoving = !a.isMoving;
    }
}
