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

    Game() throws Exception {
        new Window("Game", 914, 546, this);
        this.board = ImageIO.read(new File("./resources/board.png"));
        this.ballHandler = new BallHandler();
        start();
    }

    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
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
        stop();
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
