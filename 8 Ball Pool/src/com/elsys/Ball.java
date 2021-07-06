package com.elsys;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Ball {
    Vector2D position;
    Vector2D velocity;
    String type;
    Image image;
    int number;
    int radius;
    Rectangle2D.Double hitbox;

    public Ball(Vector2D position, Vector2D velocity, String type, Image image, int number) {
        this.position = position;
        this.velocity = velocity;
        this.type = type;
        this.number = number;
        this.radius = 15;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
        this.hitbox = new Rectangle2D.Double(position.x, position.y, 2 * radius, 2 * radius);
    }

    void tick() {
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
        hitbox.x += velocity.x;
        hitbox.y += velocity.y;
    }

    void render(Graphics g) {
        g.drawImage(image,(int)position.x, (int)position.y,null,null);
    }

}
