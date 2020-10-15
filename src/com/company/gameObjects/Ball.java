package com.company.gameObjects;

import com.company.GameWindow;
import com.company.Vector2D;
import com.company.input.Keyboard;

import java.awt.*;

public class Ball extends GameObject {

    private int size = 20;
    private double drag = 0.995d;

    public Ball(double x, double y) {
        super(x, y);
        addForce(new Vector2D(1,1));
    }

    public void update() {
        this.velocity.add(this.acceleration);
        this.position.add(this.velocity);
        this.acceleration.mult(0);

        this.edgeCollision();
        this.movement();

        this.velocity.mult(this.drag);
    }

    public void display(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval((int) this.position.x, (int) this.position.y, size, size);
    }

    public void movement(){
        double speed = 0.5d;
        Vector2D force = new Vector2D();
        if(Keyboard.UP){
            force.add(0, -speed);
        }
        if(Keyboard.LEFT){
            force.add(-speed,0);
        }
        if(Keyboard.DOWN){
            force.add(0, speed);
        }
        if(Keyboard.RIGHT){
            force.add(speed, 0);
        }
        this.addForce(force);
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
