package com.elsys;

import java.awt.*;

public abstract class Ball {
    int x, y;
    float velX = 0, velY = 0;
    String type;
    Image image;

    public Ball(int x, int y, String type, Image image){
        this.x = x;
        this.y = y;
        this.type = type;
        this.image = image;
    }

    abstract void tick();
    abstract void render(Graphics g);

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
}
