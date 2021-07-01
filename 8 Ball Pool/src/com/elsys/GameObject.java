package com.elsys;

import java.awt.*;

public abstract class GameObject {
    int x, y;
    float velX = 0, velY = 0;
    String type;

    public GameObject(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
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
