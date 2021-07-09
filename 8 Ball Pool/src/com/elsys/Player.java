package com.elsys;

import java.awt.*;

public class Player {
    String name;
    private String ballType;

    Player(String name)
    {
        this.name = name;
        this.ballType = "None";
    }

    public void render(Graphics g, BallHandler h, int x, int y, int player) {
        g.setColor(Color.black);
        g.drawString(this.name, x,y);

        int i = (player == 0) ? -20 : 20;
        for(Ball ball : h.getBalls()) {
            if(ball.getType().equals(this.ballType)) {
                g.drawImage(ball.getImage(), x + i, y + 20, null);
                if (player == 0) {
                    i += 30;
                }else{
                    i -= 30;
                }
            }
        }
    }

    String getBallType()
    {
        return this.ballType;
    }

    void setBallType(String ballType)
    {
        this.ballType = ballType;
    }
}
