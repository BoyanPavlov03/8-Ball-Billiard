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

    public boolean checkForMovement() {
        for (Ball ball : balls) {
            if (ball.velocity.x == 0 && ball.velocity.y == 0) {
                continue;
            }
            return false;
        }
        return true;
    }

    public void updateV(Ball a, Ball b, Vector2D v1, Vector2D v2){
        Vector2D X = a.position.plus(b.position.opposite());
        Vector2D V = v1.plus(v2.opposite());
        a.velocity.add(X.times(X.dot(V) / X.dot(X)).opposite());
        b.velocity.add(X.times(X.dot(V) / X.dot(X)));

    }

    public void tick(){
        for(Ball ball : balls){
            ball.tick();
            //check for collision
            for(Ball b : balls) {
                if (!ball.equals(b) && ball.hitbox.getBounds2D().intersects(b.hitbox.getBounds2D())) {
                    Vector2D v1 = new Vector2D(ball.velocity);
                    Vector2D v2 = new Vector2D(b.velocity);
                    updateV(ball, b, v1, v2);

                }
            }
            System.out.println();
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
        balls.add(new Ball(new Vector2D(150, 180), new Vector2D(0,0),"big", ImageIO.read(new File("./resources/ball 11.png")), 11));
        balls.add(new Ball(new Vector2D(150, 210), new Vector2D(0,0),"small", ImageIO.read(new File("./resources/ball 2.png")), 2));
        balls.add(new Ball(new Vector2D(150, 240), new Vector2D(0, 0),"big", ImageIO.read(new File("./resources/ball 13.png")), 13));
        balls.add(new Ball(new Vector2D(150, 270), new Vector2D(0, 0),"small", ImageIO.read(new File("./resources/ball 4.png")), 4));
        balls.add(new Ball(new Vector2D(150, 300), new Vector2D(0, 0),"small", ImageIO.read(new File("./resources/ball 5.png")), 5));
        balls.add(new Ball(new Vector2D(180, 195), new Vector2D(0, 0),"small", ImageIO.read(new File("./resources/ball 6.png")), 6));
        balls.add(new Ball(new Vector2D(180, 225), new Vector2D(0, 0),"big", ImageIO.read(new File("./resources/ball 10.png")), 10));
        balls.add(new Ball(new Vector2D(180, 255), new Vector2D(0, 0),"small", ImageIO.read(new File("./resources/ball 3.png")), 3));
        balls.add(new Ball(new Vector2D(180, 285), new Vector2D(0, 0),"big", ImageIO.read(new File("./resources/ball 14.png")), 14));
        balls.add(new Ball(new Vector2D(210, 210), new Vector2D(0, 0),"big", ImageIO.read(new File("./resources/ball 15.png")), 15));
        balls.add(new Ball(new Vector2D(210, 240), new Vector2D(0,0),"blackBall", ImageIO.read(new File("./resources/ball 8.png")), 8));
        balls.add(new Ball(new Vector2D(210, 270), new Vector2D(0,0),"small", ImageIO.read(new File("./resources/ball 1.png")), 1));
        balls.add(new Ball(new Vector2D(240, 225), new Vector2D(0, 0),"small", ImageIO.read(new File("./resources/ball 7.png")), 7));
        balls.add(new Ball(new Vector2D(240, 255), new Vector2D(0, 0),"big", ImageIO.read(new File("./resources/ball 12.png")), 12));
        balls.add(new Ball(new Vector2D(270, 240), new Vector2D(0, 0),"big", ImageIO.read(new File("./resources/ball 9.png")), 9));
    }

    public WhiteBall getWhiteBall(){
        return (WhiteBall) this.balls.getFirst();
    }

}
