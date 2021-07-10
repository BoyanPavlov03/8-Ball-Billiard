package com.elsys;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Ball {
    BallHandler ballHandler;
    Vector2D position;
    Vector2D velocity;
    String type;
    Image image;
    int number;
    int radius;
    Rectangle2D.Double hitbox;

    public Image getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public Ball(Vector2D position, Vector2D velocity, String type, Image image, int number, BallHandler ballHandler) {
        this.position = position;
        this.velocity = velocity;
        this.type = type;
        this.number = number;
        this.radius = 15;
        this.image = image.getScaledInstance(2 * radius, 2 * radius, 0);
        //this.hitbox = new Rectangle2D.Double(position.x, position.y, 2 * radius, 2 * radius);
        this.ballHandler = ballHandler;
    }
    public boolean hit(Ball b){
        return position.plus(velocity).add(b.position.opposite().plus(b.velocity)).dot(position.plus(velocity).add(b.position.opposite().add(b.velocity))) <= 960;
    }

    public boolean DeleteBall(){
        velocity.x = 0;
        velocity.y = 0;
        if(this instanceof WhiteBall){
            setPosition(new Vector2D(720, 240));
            return false;
        }
        if(!this.type.equals("blackBall"))
        {
            Main.shouldSwap = false;
            if(Main.players[Main.playerTurn].getBallType().equals("None"))
            {
                Main.players[Main.playerTurn].setBallType(this.type);
                if(this.type.equals("stripe"))
                {
                    Main.players[1 - Main.playerTurn].setBallType("solid");
                }
                else
                {
                    Main.players[1 - Main.playerTurn].setBallType("stripe");
                }
            }
        }
        else {
            if (this.type.equals("blackBall")) {
                setPosition(new Vector2D(210.2, 240.2));
                return false;
            }
        }
        System.out.println(this.type);
        ballHandler.removeBall(this);
        return true;
    }


    void setPosition(Vector2D position)
    {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    boolean tick() {
        //Returns true if a ball has been potted and false if not
        Vector2D temp = position.plus(velocity);

        if(temp.y < Main.top && temp.x + 14 > 420 && temp.x + 14 < 465){
            return DeleteBall();
        }
        if(temp.y > Main.bottom && temp.x + 14 > 420 && temp.x + 14 < 465){
            return DeleteBall();
        }
        for(int i = 0; i < 30; i++) {
            if(temp.y - 14 > (Main.bottom - 30) + i && temp.x - 14 < (Main.left - 20) + i) {
                return DeleteBall();
            }
            if(temp.y - 14 > (Main.bottom - 30) + i && temp.x + 14 > (Main.right + 20) - i) {
                return DeleteBall();
            }
            if(temp.y + 14 < (Main.top + 30) - i && temp.x - 14 < (Main.left - 20) + i) {
                return DeleteBall();
            }
            if(temp.y + 14 < (Main.top + 30) - i && temp.x + 14 > (Main.right + 20) - i) {
                return DeleteBall();
            }
        }

        if(temp.y < Main.top || temp.y > Main.bottom)
            velocity.y = -velocity.y;
        if(temp.x < Main.left || temp.x > Main.right)
            velocity.x = -velocity.x;

        /*if(temp.y < Main.top && temp.x > (Main.left + 10) && temp.x < 420){
            velocity.y = -velocity.y;
        }
        if(temp.y < Main.top && temp.x < (Main.right - 10) && temp.x > 465){
            velocity.y = -velocity.y;
        }
        if(temp.y > Main.bottom && temp.x > (Main.left + 10) && temp.x < 420){
            velocity.y = -velocity.y;
        }
        if(temp.y > Main.bottom && temp.x < (Main.right - 10) && temp.x > 465){
            velocity.y = -velocity.y;
        }
        if(temp.x < Main.left && temp.y > (Main.top + 10) && temp.y < (Main.bottom - 10)) {
            velocity.x = -velocity.x;
        }
        if(temp.x > Main.right && temp.y > (Main.top + 10) && temp.y < (Main.bottom - 10)) {
            velocity.x = -velocity.x;
        }*/


        position.add(velocity);
        double velocitySquared = velocity.times(Main.ticks * Main.ticks / 10000).dot(velocity);
        double length = velocity.length() / 100; // make into meters
        if(length <= 0)
            return false; // the ball is not moving
        double s = Math.sqrt((velocitySquared - length * Main.friction * Main.gravity - 0.25 * velocitySquared * 17 * 0.09 * length) / velocitySquared); // more physics....
        if(Double.isNaN(s))
            s = 0;
        velocity.multiply(s);
        //hitbox.x += velocity.x;
        //hitbox.y += velocity.y;
        return false;
    }

    void render(Graphics g) {
        g.drawImage(image,(int)position.x, (int)position.y,null,null);
    }

}
