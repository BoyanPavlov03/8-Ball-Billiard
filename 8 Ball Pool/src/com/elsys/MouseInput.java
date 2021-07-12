package com.elsys;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    BallHandler ballHandler;
    CueHandler cueHandler;

    public MouseInput(BallHandler ballHandler, CueHandler cueHandler) {
        this.ballHandler = ballHandler;
        this.cueHandler = cueHandler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (ballHandler.checkForMovement()) {
            ballHandler.getWhiteBall().setVelocity(new Vector2D(e.getX() - (ballHandler.getWhiteBall().position.x + 14), e.getY() - (ballHandler.getWhiteBall().position.y + 14)).normalize().multiply(3));
            Main.TurnCounter ++;
            Main.firstBallHit = true;
            Main.shouldSwap = true;
            Main.firstHit = "None";
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cueHandler.setMouseCords(new Vector2D(e.getX(), e.getY()));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        cueHandler.setMouseCords(new Vector2D(e.getX(), e.getY()));
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
