package io.thethelab;

import processing.core.PApplet;

abstract public class Circle {
    protected float x, y;
    protected float vx, vy;
    protected  float ax, ay;
    protected float r;
    protected PApplet mApplet;

    Circle(PApplet applet) {
        this.mApplet = applet;
    }

    public void render() {
        mApplet.ellipse(x, y, r, r);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getR() {
        return this.r;
    }


}
