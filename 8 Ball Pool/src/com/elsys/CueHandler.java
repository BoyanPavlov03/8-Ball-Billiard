package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class CueHandler {
    private Cue cue;
    private BallHandler ballHandler;
    private Vector2D mousePos;

    public CueHandler(Vector2D position, BallHandler ballHandler) throws Exception{
        this.cue = new Cue(new Vector2D(720, 240), ImageIO.read(new File("./resources/cue.png")));
        this.ballHandler = ballHandler;
        this.mousePos = position;
    }

    public void setMouseCords(Vector2D mousePos) {
        this.mousePos = mousePos;
    }

    public void tick(){
        cue.tick(ballHandler.getWhiteBall(), mousePos);
    }

    public void render(Graphics g) {
        if(ballHandler.checkForMovement()){
            cue.render(g);
        }
    }
}
