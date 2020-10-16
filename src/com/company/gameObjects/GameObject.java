package com.company.gameObjects;

import com.company.Game;
import com.company.GameObjectHandler;
import com.company.Vector2D;

import java.awt.*;

public abstract class GameObject {

    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;

    public final static Vector2D gravity = new Vector2D(0, .1);

    GameObjectHandler handler;

    GameObject(double x, double y, GameObjectHandler handler) {
        position = new Vector2D(x, y);
        velocity = new Vector2D();
        acceleration = new Vector2D();

        this.handler = handler;
    }

    public abstract void update();

    public abstract void display(Graphics graphics);

    public abstract void detectEdge();

    public void addForce(Vector2D force){
        acceleration.add(force);
    }
}
