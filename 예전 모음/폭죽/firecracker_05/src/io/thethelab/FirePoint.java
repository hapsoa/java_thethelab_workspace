package io.thethelab;

import processing.core.PApplet;

public class FirePoint extends Point {

//    private ArrayList<Point> pointTrace = new ArrayList<>();

    FirePoint(PApplet applet, float x, float y) {
        super(applet, x, y);
        vx = (float)Math.random() * 2.0f - 1.0f;
        vy = (float)Math.random() * 2.0f - 1.5f;
        ay = 0.02f;
//        pointTrace.add(x, y);
    }


    public void update() {
        this.x += vx;
        this.y += vy;
//        this.vx += ax;
        this.vy += ay;
//        this.ax += Math.random() * 1.0f - 0.5f;
//        this.ay += Math.random() * 1.0f - 0.5f;
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

    public float getY() {
        return this.y;
    }

    public float getVy() {
        return this.vy;
    }
}
