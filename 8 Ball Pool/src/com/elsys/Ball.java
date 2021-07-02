package com.elsys;

import java.awt.*;

public class Ball {
    int x, y;
    float velX = 0, velY = 0;
    String type;
    Image image;
    int number;

    public Ball(int x, int y, String type, Image image, int number){
        this.x = x;
        this.y = y;
        this.type = type;
        this.image = image;
        this.number = number;
        this.image = this.image.getScaledInstance(30,30, Image.SCALE_FAST);
    }

    void tick() {

    }

    void render(Graphics g) {
        g.drawImage(image, x,y,null,null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }
}
