package com.company;

import com.company.gameObjects.*;
import com.company.input.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static final double UPDATES_PER_SECOND = 60.0;
    static long frameRate = 0;

    Keyboard keyboard;
    Mouse mouse;
    Thread thread;
    private static boolean gameActive = false;

    GameObjectHandler handler = new GameObjectHandler();

    public Game() {
        keyboard = new Keyboard();
        mouse = new Mouse();
        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        handler.add(new Player(GameWindow.SCREEN_WIDTH / 2d, GameWindow.SCREEN_HEIGHT / 2d, handler));
        createAsteroids(3);
    }

    public void createAsteroids(int numberOfAsteroids) {
        for (int i = 0; i < numberOfAsteroids; i++) {
            double randomX = Math.random() * (GameWindow.SCREEN_WIDTH + 400) + 200;
            double randomY = Math.random() * (GameWindow.SCREEN_HEIGHT + 400) + 200;
            handler.add(new Asteroid(randomX, randomY, handler));
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        gameActive = true;
    }

    private void update() {
        handler.update();
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
        String text = String.format("SCORE: %S", Score.getScore());
        int textWidth =  graphics.getFontMetrics().stringWidth(text);
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
            frameRate = 1000000000 / (System.nanoTime() - now);
        }
        stop();
    }

    public synchronized void stop() {
        try {
            thread.join();
            gameActive = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
