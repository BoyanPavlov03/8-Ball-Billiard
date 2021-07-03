package com.elsys;

import javax.imageio.ImageIO;
import java.io.File;

public class WhiteBall extends Ball {

    public WhiteBall() throws Exception {
        super(720, 240,"whiteBall", ImageIO.read(new File("./resources/whiteBall.png")), 0);
        velX = 2;
        velY = 2;
    }
    public void tick(){

        x += velX;
        y += velY;

        if(x < Main.left){
            velX = -velX;
        }

        if(x > Main.right){
            velX = -velX;
        }

        if(y < Main.top){
            velY = -velY;
        }

        if(y > Main.bottom){
            velY = -velY;
        }

    }
}
