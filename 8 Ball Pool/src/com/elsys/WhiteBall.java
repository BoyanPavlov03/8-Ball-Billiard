package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class WhiteBall extends Ball {

    public WhiteBall() throws Exception {
        super(720, 240,"whiteBall", ImageIO.read(new File("./resources/whiteBall.png")), 0);
        velX = 2;
    }
    public void tick(){

        if(x < Main.left){
            velX = -velX;
        }

        if(x > Main.right){
            velX = -velX;
        }

        x -= velX;

    }
}
