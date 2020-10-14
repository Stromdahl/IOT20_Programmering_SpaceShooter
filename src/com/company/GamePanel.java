package com.company;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GamePanel extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    public static final int SCREEN_HEIGHT = 480;
    public static final int SCREEN_WIDTH = SCREEN_HEIGHT * 4 / 3;
    public static final double UPDATES_PER_SECOND = 60.0;

    Thread thread;
    private static boolean gameActive = false;

    Ball ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);

    public GamePanel() {
        new GameFrame(SCREEN_WIDTH, SCREEN_HEIGHT, this);
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

    public void run() {
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

    private void update() {
    }

    private void background(Color color, Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    private void draw() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        background(Color.black, graphics);
        ball.display(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }
}
