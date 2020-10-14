package com.company;

import java.awt.*;

public class Ball extends GameObject {

    Ball(double x, double y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void display(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval((int) this.position.x, (int) this.position.y, 20, 20);
    }
}
