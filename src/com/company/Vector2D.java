package com.company;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void add(Vector2D vector) {
        this.add(vector.x, vector.y);
    }

    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void subtract(Vector2D vector) {
        this.subtract(vector.x, vector.y);
    }

    public void mult(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public double angle() {
        return Math.atan2(this.y, this.x);
    }

    public double getAngleBetween(double x, double y) {
        return Math.atan2(this.x - x, this.y - y);
    }

    public double getAngleBetween(Vector2D vector) {
        return this.getAngleBetween(vector.x, vector.y);
    }
}
