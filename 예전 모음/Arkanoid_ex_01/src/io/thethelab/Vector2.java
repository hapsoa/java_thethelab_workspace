package io.thethelab;

public class Vector2 {

    public float x, y;

    public Vector2() {
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2 v) {
        x += v.x;
        y += v.y;
    }

    public double getDistance(Vector2 v) {
        return Math.sqrt(Math.pow(x - v.x,2) + Math.pow(y - v.y,2));
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }



}
