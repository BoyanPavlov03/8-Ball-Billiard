package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

public class BallHandler {
    private LinkedList<Ball> balls;
    private HashMap<Ball,Vector2D> velocities = new HashMap<>();

    public BallHandler() throws Exception {
        balls = new LinkedList<>();
        setupBalls();
    }

    public void removeBall(Ball ball){
        balls.remove(ball);
    }

    int getTotalBalls()
    {
        return this.balls.size();
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
        /*
        Vector2D n = a.position.plus(b.position.opposite()).normalize();
        Vector2D nt = new Vector2D(-n.y, n.x);
        a.velocity = nt.times(v1.dot(nt)).add(n.times(v2.dot(n)));
        b.velocity = nt.times(v2.dot(nt)).add(n.times(v1.dot(n)));
        */

        Vector2D X = a.position.plus(b.position.opposite());
        Vector2D V = v1.plus(v2.opposite());
        a.velocity.add(X.times(X.dot(V) / X.dot(X)).opposite());
        b.velocity.add(X.times(X.dot(V) / X.dot(X)));

    }

    public void tick(){
        for(Ball ball : balls){
            for(Ball b : balls) {
                if (b.number > ball.number && ball.hit(b)) {
                    updateV(ball, b, velocities.get(ball), velocities.get(b));
                }
            }
        }
        for(int i = 0; i < getTotalBalls(); i++){
            if(balls.get(i).tick())
            {
                i--;
                continue;
            }
            velocities.replace(balls.get(i), new Vector2D(balls.get(i).velocity));
        }

        if(checkForMovement() && Main.shouldSwap)
        {
            Main.swapTurns();
            Main.shouldSwap = false;
        }
    }

    public LinkedList<Ball> getBalls() {
        return balls;
    }

    public void render(Graphics g){
        for(Ball ball : balls){
            ball.render(g);
        }
    }

    void setupBalls() throws Exception {
        WhiteBall whiteBall = new WhiteBall(this);
        balls.add(whiteBall);
        balls.add(new Ball(new Vector2D(150, 180), new Vector2D(0,0),"stripe", ImageIO.read(new File("./resources/ball 11.png")), 11, this));
        balls.add(new Ball(new Vector2D(150.2, 210.2), new Vector2D(0,0),"solid", ImageIO.read(new File("./resources/ball 2.png")), 2, this));
        balls.add(new Ball(new Vector2D(150.4, 240.4), new Vector2D(0, 0),"stripe", ImageIO.read(new File("./resources/ball 13.png")), 13, this));
        balls.add(new Ball(new Vector2D(150.6, 270.6), new Vector2D(0, 0),"solid", ImageIO.read(new File("./resources/ball 4.png")), 4, this));
        balls.add(new Ball(new Vector2D(150.8, 300.8), new Vector2D(0, 0),"solid", ImageIO.read(new File("./resources/ball 5.png")), 5, this));
        balls.add(new Ball(new Vector2D(180, 195), new Vector2D(0, 0),"solid", ImageIO.read(new File("./resources/ball 6.png")), 6, this));
        balls.add(new Ball(new Vector2D(180.2, 225.2), new Vector2D(0, 0),"stripe", ImageIO.read(new File("./resources/ball 10.png")), 10, this));
        balls.add(new Ball(new Vector2D(180.4, 255.4), new Vector2D(0, 0),"solid", ImageIO.read(new File("./resources/ball 3.png")), 3, this));
        balls.add(new Ball(new Vector2D(180.6, 285.6), new Vector2D(0, 0),"stripe", ImageIO.read(new File("./resources/ball 14.png")), 14, this));
        balls.add(new Ball(new Vector2D(210, 210), new Vector2D(0, 0),"stripe", ImageIO.read(new File("./resources/ball 15.png")), 15, this));
        balls.add(new Ball(new Vector2D(210.2, 240.2), new Vector2D(0,0),"blackBall", ImageIO.read(new File("./resources/ball 8.png")), 8, this));
        balls.add(new Ball(new Vector2D(210.4, 270.4), new Vector2D(0,0),"solid", ImageIO.read(new File("./resources/ball 1.png")), 1, this));
        balls.add(new Ball(new Vector2D(240, 225), new Vector2D(0, 0),"solid", ImageIO.read(new File("./resources/ball 7.png")), 7, this));
        balls.add(new Ball(new Vector2D(240.2, 255.2), new Vector2D(0, 0),"stripe", ImageIO.read(new File("./resources/ball 12.png")), 12, this));
        balls.add(new Ball(new Vector2D(270, 240), new Vector2D(0, 0),"stripe", ImageIO.read(new File("./resources/ball 9.png")), 9, this));
        for(Ball b: balls) {
            velocities.put(b, new Vector2D(b.velocity));
        }
    }

    public WhiteBall getWhiteBall(){
        return (WhiteBall) this.balls.getFirst();
    }

}
