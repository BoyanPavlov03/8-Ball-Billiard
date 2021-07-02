package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;

public class BallHandler {
    private LinkedList<Ball> balls;

    public BallHandler() throws Exception {
        balls = new LinkedList<>();
        setupBalls();
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

    void setupBalls() throws Exception {
        WhiteBall whiteBall = new WhiteBall();
        balls.add(whiteBall);
        balls.add(new Ball(150, 180,"big", ImageIO.read(new File("./resources/whiteBall.png")), 12));
        balls.add(new Ball(150, 210,"small", ImageIO.read(new File("./resources/whiteBall.png")), 2));
        balls.add(new Ball(150, 240,"big", ImageIO.read(new File("./resources/whiteBall.png")), 13));
        balls.add(new Ball(150, 270,"small", ImageIO.read(new File("./resources/whiteBall.png")), 4));
        balls.add(new Ball(150, 300,"small", ImageIO.read(new File("./resources/whiteBall.png")), 5));
        balls.add(new Ball(180, 195,"small", ImageIO.read(new File("./resources/whiteBall.png")), 6));
        balls.add(new Ball(180, 225,"big", ImageIO.read(new File("./resources/whiteBall.png")), 10));
        balls.add(new Ball(180, 255,"small", ImageIO.read(new File("./resources/whiteBall.png")), 3));
        balls.add(new Ball(180, 285,"big", ImageIO.read(new File("./resources/whiteBall.png")), 14));
        balls.add(new Ball(210, 210,"big", ImageIO.read(new File("./resources/whiteBall.png")), 15));
        balls.add(new Ball(210, 240,"nigga", ImageIO.read(new File("./resources/whiteBall.png")), 8));
        balls.add(new Ball(210, 270,"small", ImageIO.read(new File("./resources/whiteBall.png")), 1));
        balls.add(new Ball(240, 225,"small", ImageIO.read(new File("./resources/whiteBall.png")), 7));
        balls.add(new Ball(240, 255,"big", ImageIO.read(new File("./resources/whiteBall.png")), 12));
        balls.add(new Ball(270, 240,"big", ImageIO.read(new File("./resources/whiteBall.png")), 9));
    }

}
