package com.elsys;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    BallHandler handler;

    public MouseInput(BallHandler h) {
        this.handler = h;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (handler.checkForMovement()) {
            handler.getWhiteBall().setVelocity(new Vector2D(e.getX() - (handler.getWhiteBall().position.x + 14), e.getY() - (handler.getWhiteBall().position.y + 14)).normalize().multiply(3));
            Main.shouldSwap = true;
            Main.TurnCounter ++;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
