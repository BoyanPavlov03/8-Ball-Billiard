package com.elsys;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Cue {
    Vector2D position;
    Image image;
    int radius;
    Vector2D mouseCords;

    public Cue(Vector2D position, Image image){
        this.position = position;
        this.radius = 150;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
        mouseCords = new Vector2D(720, 240);
    }

    public Image getImage() {
        return image;
    }

    void tick(WhiteBall whiteBall, Vector2D mousePos){
        mouseCords = mousePos;
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
        double xDistance = mouseCords.x - position.x;
        double yDistance = mouseCords.y - position.y;
        double rotationAngle = Math.atan2(yDistance, xDistance);

        //Make a backup so that we can reset our graphics object after using it.
        AffineTransform backup = g2d.getTransform();
        //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
        //is the angle to rotate the image. If you want to rotate around the center of an image,
        //use the image's center x and y coordinates for rx and ry.
        AffineTransform a = AffineTransform.getRotateInstance(rotationAngle + Main.innateRotation, position.x, position.y);
        //Set our Graphics2D object to the transform
        g2d.setTransform(a);
        //Draw our image like normal
        g2d.drawImage(image, (int) position.x, (int) position.y, null);
        //Reset our graphics object so we can draw with it again.
        g2d.setTransform(backup);
    }
}
