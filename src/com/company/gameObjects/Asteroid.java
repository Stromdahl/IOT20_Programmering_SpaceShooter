package com.company.gameObjects;

import com.company.GameObjectHandler;
import com.company.GameWindow;
import com.company.Score;
import com.company.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class Asteroid extends GameObject {

    private Score score;

    public Asteroid(double x, double y, int size, GameObjectHandler handler, Score score) {
        super(x, y, size, ID.Asteroid, handler);
        this.score = score;
        addForce(new Vector2D(Math.random() * 2 - 1, Math.random() * 2 - 1));
    }

    public Asteroid(double x, double y, GameObjectHandler handler, Score score) {
        this(x, y, 200, handler, score);
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
            if (tempGameObject.getId() == ID.Projectile) {
                if (this.position.getDistanceBetween(tempGameObject.position) < this.getSize() / 2d) {
                    score.addScore(this.getSize());
                    if (this.getSize() > 50) {
                        this.handler.add(new Asteroid(this.position.x, this.position.y, this.getSize() - 50, handler, score));
                        this.handler.add(new Asteroid(this.position.x, this.position.y, this.getSize() - 50, handler, score));
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
        graphics.drawOval((int) this.position.x - this.getSize() / 2, (int) this.position.y - this.getSize() / 2, this.getSize(), this.getSize());
    }

    public void detectEdge() {
        if (this.position.y > GameWindow.SCREEN_HEIGHT - this.getSize() / 2d) {
            this.position.y += 2 * (GameWindow.SCREEN_HEIGHT - this.getSize() / 2d - this.position.y);
            this.velocity.y = -this.velocity.y;
        }
        if (this.position.y < this.getSize() / 2d) {
            this.position.y += this.getSize() - this.position.y * 2;
            this.velocity.y = -this.velocity.y;
        }
        if (this.position.x > GameWindow.SCREEN_WIDTH - this.getSize() / 2d) {
            this.position.x += 2 * (GameWindow.SCREEN_WIDTH - this.getSize() / 2d - this.position.x);
            this.velocity.x = -this.velocity.x;
        }
        if (this.position.x < this.getSize() / 2d) {
            this.position.x += this.getSize() - this.position.x * 2;
            this.velocity.x = -this.velocity.x;
        }
    }
}
