package com.company;

public class Vector2D {
    public double x;
    public double y;

    /**
     * Returns a new vector with a specified angle
     *
     * @param angle Angle of the vector
     * @return A vector from a specified angle
     */
    public static Vector2D fromAngle(double angle) {
        return new Vector2D(Math.cos(angle), Math.sin(angle));
    }

    /**
     * Create a new vector with the given x,y components
     *
     * @param x X component
     * @param y Y component
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new vector with x,y components set to 0
     */
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Adds x,y components to the vector
     *
     * @param x X component to be added
     * @param y Y component to be added
     */
    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Adds a vector to the current vector
     *
     * @param vector The vector to be added
     */
    public void add(Vector2D vector) {
        this.add(vector.x, vector.y);
    }

    /**
     * Multiply a number to the x y components of the vector
     *
     * @param scalar How much the vector will be multiplied
     */
    public void mult(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    /**
     * Returns the calculated angle in radians
     *
     * @return double with the calculated angle
     */
    public double getAngle() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Returns the calculated angle between vector and given x,y components in radians
     *
     * @param y Y component
     * @param x X component
     * @return the calculated angle between vector ang given x,y components in radians
     */
    public double getAngleBetween(double y, double x) {
        return Math.atan2(y - this.y, x - this.x);
    }

    /**
     * Returns the calculated distance between vector and x, y components
     *
     * @param y Y component
     * @param x X component
     * @return
     */
    public double getDistanceBetween(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    /**
     * Returns the calculated distance between two vectors
     *
     * @param vector Vector
     * @return The calculated distance between two vectors
     */
    public double getDistanceBetween(Vector2D vector) {
        return getDistanceBetween(vector.x, vector.y);
    }

    /**
     * Returns the calculated magnitud of the vector
     *
     * @return The calculated magnitud of the vector
     */
    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * Set the magnitude of the vector
     *
     * @param magnitude new magnitude
     */
    public void setMagnitude(double magnitude) {
        double angle = this.getAngle();
        this.x = magnitude * Math.cos(angle);
        this.y = magnitude * Math.sin(angle);
    }

    /**
     * Rotated the vector with a given angle in radians
     *
     * @param angle Angle in radians
     */
    public void rotate(double angle) {
        double newX = x * Math.cos(angle) - y * Math.sin(angle);
        double newY = x * Math.sin(angle) + y * Math.cos(angle);
        this.x = newX;
        this.y = newY;
    }
}
