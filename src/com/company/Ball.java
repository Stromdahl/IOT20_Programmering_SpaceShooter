package com.company;

import java.awt.*;

public class Ball extends GameObject {

    int size = 20;

    Ball(double x, double y) {
        super(x, y);
        addForce(new Vector2D(.01,.01));
    }

    @Override
    public void update() {
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0);
        this.edgeCollision();
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
