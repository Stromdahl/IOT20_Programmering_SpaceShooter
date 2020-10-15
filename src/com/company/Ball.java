package com.company;

import java.awt.*;

public class Ball extends GameObject {

    private int size = 20;
    private double drag = 0.995d;

    Ball(double x, double y) {
        super(x, y);
        addForce(new Vector2D(1,1));
    }

    @Override
    public void update() {
        this.velocity.add(this.acceleration);
        this.position.add(this.velocity);
        this.acceleration.mult(0);
        this.edgeCollision();
        this.addForce(GameObject.gravity);
        this.velocity.mult(this.drag);
    }

    @Override
    public void display(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval((int) this.position.x, (int) this.position.y, size, size);
    }

    public void edgeCollision(){
        if(this.position.y + size > GameWindow.SCREEN_HEIGHT){
            this.position.y = 2 * (GameWindow.SCREEN_HEIGHT - size) - this.position.y;
            this.velocity.y = -this.velocity.y;
        }
        if(this.position.y < 0){
            this.position.y = -this.position.y;
            this.velocity.y = -this.velocity.y;
        }
        if(this.position.x + size> GameWindow.SCREEN_WIDTH){
            this.position.x = 2 * (GameWindow.SCREEN_WIDTH - size) - (this.position.x);
            this.velocity.x = -this.velocity.x;
        }
        if(this.position.x < 0){
            this.position.x = -this.position.x;
            this.velocity.x = -this.velocity.x;
        }
    }
}
