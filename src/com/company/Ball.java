package com.company;

import java.awt.*;

public class Ball extends GameObject {

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
        graphics.fillOval((int) this.position.x, (int) this.position.y, 20, 20);
    }

    public void edgeCollision(){
        if(this.position.y > GameFrame.SCREEN_HEIGHT){
            this.position.y = 2 * GameFrame.SCREEN_HEIGHT - this.position.y;
            this.velocity.y = -this.velocity.y;
        }
        if(this.position.y < 0){
            this.position.y = -this.position.y;
            this.velocity.y = -this.velocity.y;
        }
        if(this.position.x > GameFrame.SCREEN_WIDTH){
            this.position.x = 2 * GameFrame.SCREEN_WIDTH - this.position.x;
            this.velocity.x = -this.velocity.x;
        }
        if(this.position.x < 0){
            this.position.x = -this.position.x;
            this.velocity.x = -this.velocity.x;
        }
    }
}
