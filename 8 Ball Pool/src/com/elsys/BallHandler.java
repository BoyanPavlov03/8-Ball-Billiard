package com.elsys;

import java.awt.*;
import java.util.LinkedList;

public class BallHandler {
    private LinkedList<Ball> balls;

    public BallHandler() throws Exception {
        balls = new LinkedList<>();
        WhiteBall whiteBall = new WhiteBall();
        balls.add(whiteBall);

    }

    public void removeBall(Ball ball){
        balls.remove(ball);
    }

    public void tick(){
        for(Ball ball : balls){
            ball.tick();
        }
    }

    public void render(Graphics g){
        for(Ball ball : balls){
            ball.render(g);
        }
    }

}
