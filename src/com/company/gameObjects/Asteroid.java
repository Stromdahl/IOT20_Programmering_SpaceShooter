package com.company.gameObjects;

import com.company.GameObjectHandler;
import com.company.GameWindow;
import com.company.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class Asteroid extends GameObject {

    private int size;

    public Asteroid(double x, double y, int size, GameObjectHandler handler) {
        super(x, y, ID.Asteroid, handler);
        this.size = size;
        addForce(new Vector2D(Math.random() * 2 - 1, Math.random() * 2 - 1));
    }

    public Asteroid(double x, double y, GameObjectHandler handler) throws IllegalArgumentException {
        this(x, y, 200, handler);
    }

    @Override
    public void update() {
        this.velocity.add(acceleration);
        this.position.add(velocity);
        this.acceleration.mult(0);
        checkProjectileCollision();
        detectEdge();
    }

    public void checkProjectileCollision() {
        ArrayList<GameObject> gameObjects = handler.getGameObjects();
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempGameObject = gameObjects.get(i);
            if (tempGameObject.id == ID.Projectile) {
                if (this.position.getDistanceBetween(tempGameObject.position) < this.size / 2d) {
                    if (this.size > 50) {
                        this.handler.add(new Asteroid(this.position.x, this.position.y, this.size - 50, handler));
                        this.handler.add(new Asteroid(this.position.x, this.position.y, this.size - 50, handler));
                    }
                    this.handler.remove(tempGameObject);
                    this.handler.remove(this);
                }
            }
        }
    }

    @Override
    public void display(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.drawOval((int) this.position.x - size / 2, (int) this.position.y - size / 2, size, size);
    }

    public void detectEdge() {
        if (this.position.y > GameWindow.SCREEN_HEIGHT - size / 2d) {
            this.position.y += 2 * (GameWindow.SCREEN_HEIGHT - this.size / 2d - this.position.y);
            this.velocity.y = -this.velocity.y;
        }
        if (this.position.y < size / 2d) {
            this.position.y += size - this.position.y * 2;
            this.velocity.y = -this.velocity.y;
        }
        if (this.position.x > GameWindow.SCREEN_WIDTH - size / 2d) {
            this.position.x += 2 * (GameWindow.SCREEN_WIDTH - this.size / 2d - this.position.x);
            this.velocity.x = -this.velocity.x;
        }
        if (this.position.x < size / 2d) {
            this.position.x += size - this.position.x * 2;
            this.velocity.x = -this.velocity.x;
        }
    }
}
