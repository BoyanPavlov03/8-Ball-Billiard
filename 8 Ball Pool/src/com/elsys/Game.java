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
    private Font UIFont;


    Game() throws Exception {
        new Window("Game", 914, 646, this);
        this.board = ImageIO.read(new File("./resources/board.png"));
        this.ballHandler = new BallHandler();
        this.addMouseListener(new MouseInput(ballHandler));
        this.addKeyListener(new KeyInput(ballHandler.getWhiteBall()));
        this.UIFont = new Font("TimesRoman", Font.BOLD, 15);
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
        double amountOfTicks = Main.ticks;
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

        if(Main.winState != 0){
            g.setColor(Color.black);
            g.fillRect(0,0, 900, 646);
            g.setColor(Color.green);
            g.setFont(new Font("TimesRoman", Font.BOLD, 60));
            if(Main.winState == -1){
                Main.swapTurns();
                Main.winState = 1;
            }
            String str = Main.players[Main.playerTurn].name + " has won the game!";
            g.drawString(str, 65, 323);
        }else {
            g.setColor(Color.white);
            g.fillRect(0, 0, 900, 646);
            g.drawImage(board, 0, 0, null, null);
            ballHandler.render(g);
            g.setFont(UIFont);
            Main.players[0].render(g, ballHandler, Main.left - 30, 550, 0);
            Main.players[1].render(g, ballHandler, Main.right, 550, 1);
            g.drawString(Main.players[Main.playerTurn].name, (Main.right + Main.left - 35) / 2, 525);
        }
        ////////////////////////////////
        g.dispose();
        bs.show();
    }
}
