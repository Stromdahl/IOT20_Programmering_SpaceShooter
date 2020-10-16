package com.company.gameObjects;

import com.company.Vector2D;
import com.company.input.Keyboard;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

public class Player extends GameObject {

    private final double decelerationRate = 0.99d;
    private final double accelerationRate = 0.1d;
    private final double turnSpeed = 0.05;

    private double headingAngle = 0;

    public Player(double x, double y) {
        super(x, y);
    }

    public void update() {
        Vector2D newVelocity = Vector2D.fromAngle(this.headingAngle);
        newVelocity.setMagnitude(this.velocity.getMagnitude());
        newVelocity.add(this.acceleration);
        this.velocity = newVelocity.copy();
        this.position.add(this.velocity);
        this.acceleration.mult(0);

        this.movement();
    }

    private void movement() {

        Vector2D force = new Vector2D();
        if (Keyboard.UP) {
            accelerate();
        } else {
            this.velocity.mult(decelerationRate);
        }
        if (Keyboard.LEFT) {
            this.headingAngle -= turnSpeed;
        }
        if (Keyboard.DOWN) {
            this.velocity.mult(0.95);
        }
        if (Keyboard.RIGHT) {
            this.headingAngle += turnSpeed;
        }
        this.addForce(force);
    }

    public void accelerate(){
        Vector2D force = Vector2D.fromAngle(this.headingAngle);
        force.setMagnitude(this.accelerationRate);
        addForce(force);
    }

    public void display(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform old = g2d.getTransform();

        g2d.translate(this.position.x, this.position.y);
        g2d.rotate(headingAngle);

        drawShip(6, graphics);

        g2d.setTransform(old);
    }

    public void drawShip(double scalar, Graphics graphics){
        Parser doublesToInts = (d) -> Arrays.stream(d).map(i -> i * scalar).mapToInt(i -> (int) Math.round(i)).toArray();

        graphics.setColor(Color.GREEN);
        double[] xPoints = {1.5, -1.5, -0.5, -1.5};
        double[] yPoints = {0, 1, 0, -1};
        graphics.drawPolygon(doublesToInts.map(xPoints), doublesToInts.map(yPoints), xPoints.length);
    }
    interface Parser{
        int[] map(double[] doubles);
    }
}