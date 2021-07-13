package com.elsys;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Vector;

public class Cue {
    Vector2D position;
    Image image;
    int radius;
    Vector2D mouseCords;
    double xDistance;
    double yDistance;
    double rotationAngle;
    double dist;
    double distx;
    double disty;
    double whatper;
    double addx;
    double addy;
    double maxpull;
    Vector2D pull;
    Vector2D oldPos;

    public Cue(Vector2D position, Image image){
        this.position = position;
        this.oldPos = new Vector2D(position.x, position.y);
        this.radius = 150;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
        mouseCords = new Vector2D(720, 240);
        this.xDistance = mouseCords.x - position.x;
        this.yDistance = mouseCords.y - position.y;
        this.rotationAngle = Math.atan2(yDistance, xDistance);

        this.dist = mouseCords.distance(new Vector2D(720, 240));
        this.distx = 720 - mouseCords.x;
        this.disty = 240 - mouseCords.y;
        this.whatper = (15 / this.dist) * 100;
        this.addx = (this.distx * this.whatper) / 100;
        this.addy = (this.disty * this.whatper) / 100;
        this.maxpull = 50;
        this.pull = new Vector2D(0, 0);
    }

    public Image getImage() {
        return image;
    }

    void tick(WhiteBall whiteBall, Vector2D mousePos, boolean shooting){
        if(!shooting) {
            mouseCords = mousePos;
            Vector2D whiteBallPos = new Vector2D(whiteBall.position.x + 14, whiteBall.position.y + 14);
            dist = whiteBallPos.distance(mousePos);
            distx = whiteBallPos.x - mousePos.x;
            disty = whiteBallPos.y - mousePos.y;
            whatper = (15 / dist) * 100;
            addx = (distx * whatper) / 100;
            addy = (disty * whatper) / 100;

            position.x = whiteBallPos.x + addx;
            position.y = whiteBallPos.y + addy;
            oldPos.x = position.x;
            oldPos.y = position.y;
        }else{
            // mousePos - current, mouseCords - old
            pull.x = mousePos.x - mouseCords.x;
            pull.y = mousePos.y - mouseCords.y;
            position.x = oldPos.x + pull.x;
            position.y = oldPos.y + pull.y;
        }
    }

    void render(Graphics g, boolean shooting) {
        Graphics2D g2d = (Graphics2D) g.create();
        if(!shooting) {
            xDistance = mouseCords.x - position.x;
            yDistance = mouseCords.y - position.y;
            rotationAngle = Math.atan2(yDistance, xDistance);
        }

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
