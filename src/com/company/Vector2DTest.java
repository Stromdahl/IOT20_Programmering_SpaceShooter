package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Vector2DTest {
    @Test
    @DisplayName("Test add x y components to the vector")
    void testAddXYComponents() {
        Vector2D vector = new Vector2D();
        vector.add(1, 2);
        assertEquals(1, vector.x);
        assertEquals(2, vector.y);
    }

    @Test
    @DisplayName("Test add a vector to the vector")
    void testAddVector() {
        Vector2D vector = new Vector2D();
        vector.add(new Vector2D(1, 2));
        assertEquals(1, vector.x);
        assertEquals(2, vector.y);
    }

    @Test
    @DisplayName("Test Multiply x y components width a scalar")
    void testMultiplyVector() {
        Vector2D vector = new Vector2D(1, 2);
        vector.mult(2);
        assertEquals(2, vector.x);
        assertEquals(4, vector.y);
    }

    @Test
    @DisplayName("Test Get the angle of the vector in radians")
    void testGetAngle() {
        Vector2D vector = new Vector2D(-2, 0);
        assertEquals(Math.PI, vector.getAngle());
    }

    @Test
    @DisplayName("Test Get the angle between the vector and x,y components")
    void testGetAngleBetweenXYComponents() {
        Vector2D vector = new Vector2D(2, 0);
        assertEquals(Math.PI, vector.getAngleBetween(0, 0));
    }

    @Test
    @DisplayName("Test Get the distance between the vector and x,y components")
    void testGetDistanceBetweenXYComponents() {
        Vector2D vector = new Vector2D(0, 0);
        assertEquals(5, vector.getDistanceBetween(3, 4));
    }

    @Test
    @DisplayName("Test Get the angle between vectors")
    void testGetDistanceBetweenVectors() {
        Vector2D vector = new Vector2D(0, 0);
        assertEquals(5, vector.getDistanceBetween(new Vector2D(3, 4)));
    }

    @Test
    @DisplayName("Test get the magnitude of the vector")
    void testGetMagnitude() {
        Vector2D vector = new Vector2D(3, 4);
        assertEquals(5, vector.getMagnitude());
    }

    @Test
    @DisplayName("Test set the magnitude")
    void testSetMagnitudeNewValue() {
        Vector2D vector = new Vector2D();
        vector.setMagnitude(5);
        assertEquals(5, vector.getMagnitude());
    }

    @Test
    @DisplayName("Test set the magnitude")
    void testSetMagnitudeNotDefaultValue() {
        Vector2D vector = new Vector2D();
        vector.setMagnitude(5);
        assertNotEquals(0, vector.getMagnitude());
    }

    @Test
    @DisplayName("Test rotate vector with a angle in radians")
    void testRotateAtAngle() {
        Vector2D vector = new Vector2D(2, 0);
        vector.rotate(Math.PI);
        assertEquals(Math.PI, vector.getAngle());
    }

    @Test
    @DisplayName("Test Create a vector of a specified angle")
    void testCreateVectorFromAngle() {
        Vector2D vector = Vector2D.fromAngle(Math.PI);
        assertEquals(Math.PI, vector.getAngle());
    }
}
