package io.thethelab;

import processing.core.PApplet;

public abstract class Point implements Draw {
    protected float x, y;
    protected float vx, vy;
    protected float ax, ay;
    protected PApplet mApplet;

    Point(PApplet applet, float x, float y) {
        this.x = x;
        this.y = y;
        this.mApplet = applet;

//        pointTrace.add(x, y);
    }

    abstract public float getY();


}
