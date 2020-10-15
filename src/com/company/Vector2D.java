package com.company;

public class Vector2D {
    public double x;
    public double y;

    public static Vector2D fromAngle(double angle){
        return new Vector2D(Math.cos(angle), Math.sin(angle));
    }

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

    public double getAngleBetween(double y, double x) {
        return Math.atan2(y - this.y, x - this.x);
    }

    public double getAngleBetween(Vector2D vector) {
        return this.getAngleBetween(vector.y, vector.x);
    }

    public void setMagnitude(double magnitude){
        double angle = this.angle();
        this.x = magnitude * Math.cos(angle);
        this.y = magnitude * Math.sin(angle);
    }

    public double getMagnitude(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
}
