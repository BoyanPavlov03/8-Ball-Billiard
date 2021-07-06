package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class WhiteBall extends Ball {
    public WhiteBall() throws Exception {
        super(new Vector2D(720, 240), new Vector2D(0.0,0.0),"whiteBall", ImageIO.read(new File("./resources/whiteBall.png")), 0);

    }

    void setVelocity(Vector2D v) {
        this.velocity = v;
    }

    public void tick(){
        Vector2D temp = position.plus(velocity);

        if(temp.y < Main.top || temp.y > Main.bottom)
            velocity.y = -velocity.y;
        if(temp.x < Main.left || temp.x > Main.right)
            velocity.x = -velocity.x;

        position.add(velocity);
        double velocitySquared = velocity.times(Main.ticks / 100).dot(velocity); // make it into meters/seconds
        double length = velocity.length() / 100; // make into meters
        if(length <= 0)
            return; // the ball is not moving
        double s = Math.sqrt((velocitySquared - length * Main.friction * Main.gravity) / velocitySquared); // more physics....
        if(Double.isNaN(s))
            s = 0;
        velocity.multiply(s);
    }


}
