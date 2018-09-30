package io.thethelab;

import processing.core.PApplet;

public class PlayerCircle extends Circle {
    private int heartCount;

    public PlayerCircle(PApplet applet, int heartCount) {
        super(applet);
        x = 480;
        y = 320;
        r = 50;
        vx = 5.0f;
        vy = 5.0f;
        this.heartCount = heartCount;
    }
//    public setHeartCount()

    public void moveLeft() {
        x -= vx;
    }

    public void moveRight() {
        x += vx;
    }

    public void moveUp() {
        y -= vy;
    }

    public void moveDown() {
        y += vy;
    }

    public int getHeartCount() {
        return this.heartCount;
    }

    public void subHeartCount() {
        this.heartCount--;
    }

    public void render() {
        mApplet.ellipse(x, y, r, r);
        mApplet.fill(255, 0, 0);

        if (this.heartCount == 3) {
            mApplet.ellipse(20, 20, 10, 10);
            mApplet.ellipse(40, 20, 10, 10);
            mApplet.ellipse(60, 20, 10, 10);
        }

        if (this.heartCount == 2) {
            mApplet.ellipse(20, 20, 10, 10);
            mApplet.ellipse(40, 20, 10, 10);
        }

        if (this.heartCount == 1)
            mApplet.ellipse(20, 20, 10, 10);
    }


}
