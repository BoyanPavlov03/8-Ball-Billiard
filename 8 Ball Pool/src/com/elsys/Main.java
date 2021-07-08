package com.elsys;

public class Main {
    static int left = 57;
    static int top = 60;
    static int bottom = 420;
    static int right = 813;
    static double friction = 0.02;
    static double gravity = 9.8;
    static double ticks = 300;
    static int playerTurn = 0;
    static Player[] players = new Player[2];

    public static void swapTurns()
    {
        playerTurn = 1 - playerTurn;
    }

    public static void main(String[] args) throws Exception {
        players[0] = new Player("Player1");
        players[1] = new Player("Player2");
        new Game();
    }
}
