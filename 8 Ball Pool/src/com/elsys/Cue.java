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
        this.radius = 150;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
    }

    void tick(WhiteBall whiteBall, Vector2D mousePos){
        Vector2D whiteBallPos = new Vector2D(whiteBall.position.x + 14, whiteBall.position.y + 14);
        double dist = whiteBallPos.distance(mousePos);
        double distx = whiteBallPos.x - mousePos.x;
        double disty = whiteBallPos.y - mousePos.y;
        double whatper = (15 / dist) * 100;
        double addx = (distx * whatper) / 100;
        double addy = (disty * whatper) / 100;



        position.x = whiteBallPos.x + addx;
        position.y = whiteBallPos.y + addy;
    }

    void render(Graphics g) {
        g.drawImage(image,(int)position.x, (int)position.y,null,null);
    }
}
