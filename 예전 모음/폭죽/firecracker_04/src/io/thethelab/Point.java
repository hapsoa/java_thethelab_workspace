package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public abstract class Point implements Draw {
    protected float x, y;
    protected float vx, vy;
    protected float ax, ay;
    protected PApplet mApplet;
    protected ArrayList<Float> lx;
    protected ArrayList<Float> ly;

    Point(PApplet applet, float x, float y) {
        this.x = x;
        this.y = y;
        this.mApplet = applet;
        this.lx = new ArrayList<>();
        this.ly = new ArrayList<>();

//        pointTrace.add(x, y);
    }

    abstract public float getY();


}
