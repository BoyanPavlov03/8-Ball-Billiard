package com.elsys;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MouseInput extends MouseAdapter {
    BallHandler ballHandler;
    CueHandler cueHandler;
    Vector2D mouseCords;

    public MouseInput(BallHandler ballHandler, CueHandler cueHandler) {
        this.ballHandler = ballHandler;
        this.cueHandler = cueHandler;
        this.mouseCords = new Vector2D(720, 240);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*if (ballHandler.checkForMovement()) {
            ballHandler.getWhiteBall().setVelocity(new Vector2D(e.getX() - (ballHandler.getWhiteBall().position.x + 14), e.getY() - (ballHandler.getWhiteBall().position.y + 14)).normalize().multiply(3));
            Main.TurnCounter ++;
            Main.firstBallHit = true;
            Main.shouldSwap = true;
            Main.firstHit = "None";
        }*/
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cueHandler.setMouseCords(new Vector2D(e.getX(), e.getY()));
        cueHandler.setShooting(false);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        cueHandler.setMouseCords(new Vector2D(e.getX(), e.getY()));
        cueHandler.setShooting(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseCords = new Vector2D(e.getX(), e.getY());
        System.out.println("Got mouse cords");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released");
        if (ballHandler.checkForMovement()) {
            Main.TurnCounter ++;
            ballHandler.getWhiteBall().setVelocity(new Vector2D(mouseCords.x - (ballHandler.getWhiteBall().position.x + 14), mouseCords.y - (ballHandler.getWhiteBall().position.y + 14)).normalize().multiply(3));
            Main.firstBallHit = true;
            Main.shouldSwap = true;
            Main.firstHit = "None";
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
