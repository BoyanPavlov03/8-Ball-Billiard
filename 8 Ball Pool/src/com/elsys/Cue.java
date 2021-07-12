package com.elsys;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;

public class Cue {
    Vector2D position;
    Image image;
    int radius;

    public Cue(Vector2D position, Image image){
        this.position = position;
        this.radius = 150;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
    }

    public Image getImage() {
        return image;
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
        Graphics2D g2d = (Graphics2D) g.create();

        AffineTransform backup = g2d.getTransform();
        AffineTransform a = AffineTransform.getRotateInstance(Math.toRadians(200), position.x, position.y);
        g2d.setTransform(a);
        g2d.drawImage(image, (int) position.x, (int) position.y, null);
        g2d.setTransform(backup);
    }
}
