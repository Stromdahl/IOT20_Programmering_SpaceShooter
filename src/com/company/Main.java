package com.company;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        new GameWindow(game);
        game.start();
    }
}
