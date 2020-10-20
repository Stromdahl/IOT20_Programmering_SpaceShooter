package com.company;

import com.company.gameObjects.*;
import com.company.input.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    GameWindow gameWindow;
    private final double UPDATES_PER_SECOND = 60.0;
    public static boolean gameActive = false;
    private int updateCounter = 0;
    Keyboard keyboard = new Keyboard();
    Mouse mouse = new Mouse();
    GameObjectHandler handler = new GameObjectHandler();
    Score score = new Score();
    Thread thread;

    public Game() {
        gameWindow = new GameWindow(this);
        addListeners();
        createPlayer();
        createAsteroids(3);
    }

    private void addListeners() {
        this.addKeyListener(keyboard);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    private void createPlayer() {
        handler.add(new Player(GameWindow.SCREEN_WIDTH / 2d, GameWindow.SCREEN_HEIGHT / 2d, handler));
    }

    private void createAsteroids(int numberOfAsteroids) {
        for (int i = 0; i < numberOfAsteroids; i++) {
            double randomX = Math.random() * (GameWindow.SCREEN_WIDTH + 400) - 200;
            double randomY = Math.random() * (GameWindow.SCREEN_HEIGHT + 400) - 200;
            handler.add(new Asteroid(randomX, randomY, handler, score));
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        gameActive = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            gameActive = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void askPlayerForName(){

    }

    private void update() {
        if(handler.hasPlayer()){
            handler.update();
            updateCounter++;
            if(updateCounter % (UPDATES_PER_SECOND * 5) == 0){
                createAsteroids(1);
            }
        } else {
            gameWindow.dispatchEvent(new WindowEvent(gameWindow, WindowEvent.WINDOW_CLOSING));
            this.stop();
        }
    }

    private void draw() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();
        enableAntialiasing((Graphics2D) graphics);
        drawBackground(Color.black, graphics);
        drawScoreOnScreen(GameWindow.SCREEN_WIDTH / 2, 32, 32, graphics);
        handler.display(graphics);
        graphics.dispose();
        bufferStrategy.show();
    }

    private void enableAntialiasing(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void drawScoreOnScreen(int x, int y, int size, Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Monospaced", Font.BOLD, size));
        String text = String.format("SCORE: %S", score.getScore());
        int textWidth = graphics.getFontMetrics().stringWidth(text);
        graphics.drawString(text, x - textWidth / 2, y);
    }

    private void drawBackground(Color color, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    public void run() {
        this.createBufferStrategy(3);
        long lastTime = System.nanoTime();
        double nanoSecondsPerFrame = 1000000000 / UPDATES_PER_SECOND;
        double delta = 0;
        while (gameActive) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSecondsPerFrame;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (gameActive) {
                draw();
            }
        }
        stop();
    }
}
