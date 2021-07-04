package com.elsys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;


public class Game extends Canvas implements Runnable{
    private boolean isRunning = false;
    private Thread thread;
    private Image board;
    private BallHandler ballHandler;
    static double friction = 0.02;
    static double gravity = 9.8;
    static double ticks = 300;

    Game() throws Exception {
        new Window("Game", 914, 546, this);
        this.board = ImageIO.read(new File("./resources/board.png"));
        this.ballHandler = new BallHandler();
        this.addKeyListener(new KeyInput(ballHandler.getWhiteBall()));
        start();
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() throws Exception {
        isRunning = false;
        thread.join();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = ticks;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }

        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tick(){
        ballHandler.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            this.createBufferStrategy(3); // render at 2 more frames in advance for clean rendering
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ////////////////////////////////

        g.setColor(Color.white);
        g.fillRect(0 ,0, 900, 509);
        g.drawImage(board, 0, 0, null, null);
        ballHandler.render(g);

        ////////////////////////////////
        g.dispose();
        bs.show();
    }
}
