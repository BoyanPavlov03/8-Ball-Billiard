package com.elsys;

import java.awt.*;

public class Cue {
    Vector2D position;
    Image image;
    int radius;

    public Image getImage() {
        return image;
    }

    public Cue(Vector2D position, Image image){
        this.position = position;
        this.radius = 100;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
    }

    void tick(WhiteBall whiteBall){
        position.x = whiteBall.position.x;
        position.y = whiteBall.position.y;
    }

    void render(Graphics g) {
        g.drawImage(image,(int)position.x, (int)position.y,null,null);
    }
}
