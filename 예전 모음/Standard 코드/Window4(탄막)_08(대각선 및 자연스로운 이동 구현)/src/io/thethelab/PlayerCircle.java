package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

public class PlayerCircle extends Circle {
    private int heartCount;
    PImage photo;

    public PlayerCircle(PApplet applet, int heartCount) {
        super(applet);
        x = 480;
        y = 320;
        r = 50;
        vx = 3.0f;
        vy = 3.0f;
        this.heartCount = heartCount;

    }
//    public setHeartCount()

    public void moveLeft() {
        if (vx < 7)
            vx *= 1.01f;
        x -= vx;
    }

    public void moveRight() {
        if (vx < 7)
            vx *= 1.01f;
        x += vx;
    }

    public void moveUp() {
        if (vy < 7)
            vy *= 1.01f;
        y -= vy;
    }

    public void moveDown() {
        if (vy < 7)
            vy *= 1.01f;
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

        photo = mApplet.loadImage("heart.png");
        if (this.heartCount == 3) {

            mApplet.image(photo, 30, 20, 15, 15);
            mApplet.image(photo, 60, 20, 15, 15);
            mApplet.image(photo, 90, 20, 15, 15);
        }

        if (this.heartCount == 2) {
            mApplet.image(photo, 30, 20, 15, 15);
            mApplet.image(photo, 60, 20, 15, 15);
        }

        if (this.heartCount == 1)
            mApplet.image(photo, 30, 20, 15, 15);
    }


}
