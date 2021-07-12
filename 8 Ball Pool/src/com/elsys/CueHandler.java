package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class CueHandler {
    private Cue cue;
    BallHandler ballHandler;

    public CueHandler(BallHandler bh) throws Exception{
        cue = new Cue(new Vector2D(720, 240), ImageIO.read(new File("./resources/cue.png")));
        ballHandler = bh;
    }

    void tick(){
        cue.tick(ballHandler.getWhiteBall());
    }

    void render(Graphics g) {
        if(ballHandler.checkForMovement()){
            cue.render(g);
        }
    }
}
