package com.company.gameObjects;

import com.company.GameObjectHandler;
import com.company.Vector2D;

import java.awt.*;

public abstract class GameObject {

    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;
    private int size;
    private ID id;
    GameObjectHandler handler;

    GameObject(double x, double y, int size, ID id, GameObjectHandler handler) {
        position = new Vector2D(x, y);
        velocity = new Vector2D();
        acceleration = new Vector2D();
        this.id = id;
        this.handler = handler;
        this.size = size;
    }

    public abstract void update();

    public abstract void display(Graphics graphics);

    public abstract void detectEdge();

    public void addForce(Vector2D force) {
        acceleration.add(force);
    }

    public int getSize() {
        return size;
    }

    public ID getId() {
        return id;
    }
}
