package com.company.gameObjects;

import com.company.GameObjectHandler;
import com.company.Vector2D;

import java.awt.*;

public class Projectile extends GameObject {
    int size = 5;
    double heading;

    Projectile(double x, double y, double heading, GameObjectHandler handler) {
        super(x, y, handler);
        this.heading = heading;
        Vector2D force = Vector2D.fromAngle(heading);
        force.setMagnitude(6);
        addForce(force);
    }

    @Override
    public void update() {
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0);
    }

    @Override
    public void display(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillOval((int)this.position.x, (int)this.position.y, size,size);
    }

    @Override
    public void detectEdge() {

    }
}
