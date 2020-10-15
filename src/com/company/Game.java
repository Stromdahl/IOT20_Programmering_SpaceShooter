package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static final double UPDATES_PER_SECOND = 60.0;

    Thread thread;
    private static boolean gameActive = false;

    Ball ball = new Ball(GameWindow.SCREEN_WIDTH / 2, GameWindow.SCREEN_HEIGHT / 2);

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        gameActive = true;
    }

    private void update() {
        ball.update();
    }

    private void background(Color color, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    private void draw() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        background(Color.black, graphics);
        ball.display(graphics);

        graphics.dispose();
        bufferStrategy.show();
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
            long frameRate = 1000000000 / (System.nanoTime() - now);
            System.out.println(frameRate);
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
