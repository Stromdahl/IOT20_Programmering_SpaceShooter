package com.company.gameObjects;

import com.company.GameObjectHandler;
import com.company.GameWindow;
import com.company.Vector2D;
import com.company.input.Keyboard;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

public class Player extends GameObject {

    private final double decelerationRate = 0.99d;
    private final double accelerationRate = 0.1d;
    private final double turnSpeed = 0.05;
    private final double maxSpeed = 10;
    private final int weaponCooldownTime = 10;
    private int cooldownTimer = 0;
    private double headingAngle = 0;
    private boolean accelerating = false;

    public Player(double x, double y, GameObjectHandler handler) {
        super(x, y, ID.Player,handler);
    }

    public void update() {
        this.movement();

        Vector2D newVelocity = Vector2D.fromAngle(this.headingAngle);
        newVelocity.setMagnitude(Math.min(this.velocity.getMagnitude(), this.maxSpeed));
        newVelocity.add(this.acceleration);

        this.velocity = newVelocity.copy();
        this.position.add(this.velocity);
        this.acceleration.mult(0);

        this.detectEdge();

        if(this.cooldownTimer > 0){
            this.cooldownTimer--;
        }
    }

    private void movement() {
        Vector2D force = new Vector2D();
        if (Keyboard.UP) {
            accelerate();
            accelerating = true;
        } else {
            this.velocity.mult(decelerationRate);
            accelerating = false;
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
        if (Keyboard.SPACE) {
            this.shootProjectile();
        }
        this.addForce(force);
    }

    private void shootProjectile() {
        if(cooldownTimer < 1) {
            handler.add(new Projectile(this.position.x, this.position.y, this.headingAngle, handler));
            cooldownTimer = weaponCooldownTime;
        }
    }

    public void accelerate(){
        Vector2D force = Vector2D.fromAngle(this.headingAngle);
        force.setMagnitude(this.accelerationRate);
        addForce(force);
    }

    public void detectEdge() {
        if(this.position.x > GameWindow.SCREEN_WIDTH) this.position.x -= GameWindow.SCREEN_WIDTH;
        else if(this.position.x < 0) this.position.x += GameWindow.SCREEN_WIDTH;

        if(this.position.y > GameWindow.SCREEN_HEIGHT) this.position.y -= GameWindow.SCREEN_HEIGHT;
        else if(this.position.y < 0) this.position.y += GameWindow.SCREEN_HEIGHT;
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

        if(this.accelerating) {
            graphics.setColor(Color.ORANGE);
            double[] xPointsFlame = {-1.5, -2.0, -3.0, -2.0};
            double[] yPointsFlame = {0, 0.5, 0, -0.5};
            graphics.drawPolygon(doublesToInts.map(xPointsFlame), doublesToInts.map(yPointsFlame), xPoints.length);
        }
    }

    interface Parser{
        int[] map(double[] doubles);
    }
}