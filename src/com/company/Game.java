package com.company;

import com.company.input.Mouse;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    static long frameRate = 0;

    private static final long serialVersionUID = 1L;

    public static final double UPDATES_PER_SECOND = 60.0;

    Thread thread;
    private static boolean gameActive = false;

    Ball ball = new Ball(GameWindow.SCREEN_WIDTH / 2d, GameWindow.SCREEN_HEIGHT / 2d);

    public Game() {
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        gameActive = true;
    }

    private void update() {
        ball.update();

        if(Mouse.getButton() == 1){
            Vector2D force = Vector2D.fromAngle(ball.position.getAngleBetween(Mouse.getY(), Mouse.getX()));
            force.setMagnitude(1);
            ball.addForce(force);
        }
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
