package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class WhiteBall extends Ball {
    boolean isMoving = false;
    public WhiteBall() throws Exception {
        super(new Vector2D(720, 240), new Vector2D(0.5,0.5),"whiteBall", ImageIO.read(new File("./resources/whiteBall.png")), 0);

    }
    public void tick(){
        if(!isMoving)
            return;
        Vector2D temp = position.plus(velocity);
        if(temp.y < Main.top || temp.y > Main.bottom)
            velocity.y = -velocity.y;
        if(temp.x < Main.left || temp.x > Main.right)
            velocity.x = -velocity.x;

        position.add(velocity);
        double velocitySquared = velocity.times(Game.ticks / 100).dot(velocity); // make it into meters/seconds
        double length = velocity.length() / 100; // make into meters
        if(length <= 0)
            return; // the ball is not moving
        double s = Math.sqrt((velocitySquared - length * Game.friction * Game.gravity) / velocitySquared); // more physics....
        if(Double.isNaN(s))
            s = 0;
        velocity.multiply(s);

    }
}
