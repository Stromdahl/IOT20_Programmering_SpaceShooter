package com.company.gameObjects;

import com.company.GameObjectHandler;
import com.company.GameWindow;
import com.company.Vector2D;

import java.awt.*;

public class Projectile extends GameObject {
    private final int size = 5;
    double heading;

    Projectile(double x, double y, double heading, GameObjectHandler handler) {
        super(x, y, ID.Projectile, handler);
        this.heading = heading;
        Vector2D force = Vector2D.fromAngle(heading);
        force.setMagnitude(10);
        addForce(force);
    }

    @Override
    public void update() {
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0);
        detectEdge();
    }

    @Override
    public void display(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillOval((int) this.position.x, (int) this.position.y, size, size);
    }

    @Override
    public void detectEdge() {
        if (this.position.x > GameWindow.SCREEN_WIDTH || this.position.x < 0 || this.position.y > GameWindow.SCREEN_HEIGHT || this.position.y < 0) {
            this.handler.remove(this);
        }
    }
}
