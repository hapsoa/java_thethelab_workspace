package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public class StartPoint extends Point {

//    private ArrayList<Point> pointTrace = new ArrayList<>();

    StartPoint(PApplet applet, float x, float y) {
        super(applet, x, y);
        vy = 2.0f;
        ay = 0.01f;
//        pointTrace.add(x, y);
    }

    public void update() {
        this.y -= vy;
        this.vy -= ay;
    }

    public void render() {
        mApplet.ellipse(x, y, 10, 10);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }

    public float getVy() {
        return this.vy;
    }

}
