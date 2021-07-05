package com.elsys;

import java.awt.*;

public class Ball {
    Vector2D position;
    Vector2D velocity;
    String type;
    Image image;
    int number;
    int radius;

    public Ball(Vector2D position, Vector2D velocity, String type, Image image, int number) {
        this.position = position;
        this.velocity = velocity;
        this.type = type;
        this.number = number;
        this.radius = 15;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
    }

    void tick() {

    }

    void render(Graphics g) {
        g.drawImage(image,(int)position.x, (int)position.y,null,null);
    }

}
