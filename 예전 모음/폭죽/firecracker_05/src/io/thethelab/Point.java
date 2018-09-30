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

    public void renderLine() {

        this.lx.add(this.x);
        this.ly.add(this.y);

        this.update();
        // 2.점의 저장된 위치에 따라 라인을 그려간다.
        mApplet.fill(255, 255, 255);
        this.render();
        for (int i = 1; i < this.lx.size(); i++) {
            mApplet.stroke(255);
            mApplet.line(this.lx.get(i-1), this.ly.get(i-1),
                    this.lx.get(i), this.ly.get(i));
        }
        if (this.lx.size() > 30) { //꼬리 없애기
            this.lx.remove(0);
            this.ly.remove(0);
        }
    }


}
