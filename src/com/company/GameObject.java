package com.company;

import java.awt.*;

public abstract class GameObject {

    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;

    GameObject(double x, double y) {
        position = new Vector2D(x, y);
        velocity = new Vector2D();
        acceleration = new Vector2D();
    }

    public abstract void update();

    public abstract void display(Graphics graphics);

    public void addForce(Vector2D force){
        acceleration.add(force);
    }
}
