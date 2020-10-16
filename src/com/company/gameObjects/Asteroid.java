package com.company.gameObjects;

import com.company.GameWindow;
import com.company.Vector2D;

import java.awt.*;

public class Asteroid extends GameObject{

    private int size;

    public Asteroid(double x, double y) {
        super(x, y);
        size = (int)Math.round(Math.random()* 3 + 1) * 50;
        addForce(new Vector2D(Math.random() * 2 - 1, Math.random() * 2 - 1));
    }

    @Override
    public void update() {
        this.velocity.add(acceleration);
        this.position.add(velocity);
        this.acceleration.mult(0);
        bounceOnEdge();
    }

    @Override
    public void display(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.drawOval((int) this.position.x, (int)this.position.y, size,size);
    }

    public void bounceOnEdge(){
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
