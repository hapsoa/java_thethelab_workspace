package io.thethelab;

import processing.core.PApplet;

public class UpFirePoint extends Point {

    UpFirePoint(PApplet applet, float x, float y) {
        super(applet, x, y);
        vx = (float)Math.random() * 2.0f - 1.0f;
        vy = -(float)Math.random() * 2.0f;
        ay = 0.02f;
    }

    @Override
    public void update() {
        this.x += vx;
        this.y += vy;
//        this.vx += ax;
//        this.vy += ay;
    }

    @Override
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
