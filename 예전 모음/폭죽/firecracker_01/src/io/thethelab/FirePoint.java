package io.thethelab;

import processing.core.PApplet;

public class FirePoint {
    private float x;
    private float y;
    private float vx;
    private float vy = 2.0f;
    private float ax;
    private float ay = 0.01f;
    private PApplet mApplet;
//    private ArrayList<Point> pointTrace = new ArrayList<>();

    FirePoint(PApplet applet, float x, float y) {
        this.x = x;
        this.y = y;
        this.mApplet = applet;

//        pointTrace.add(x, y);
    }

    void update() {
        this.y -= vy;
        this.vy -= ay;
    }

    void render() {
        mApplet.ellipse(x, y, 10, 10);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return this.y;
    }

    public float getVy() {
        return this.vy;
    }
}
