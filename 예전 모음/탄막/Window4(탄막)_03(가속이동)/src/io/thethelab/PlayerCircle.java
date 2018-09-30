package io.thethelab;

import processing.core.PApplet;

public class PlayerCircle extends Circle {

    public PlayerCircle(PApplet applet) {
        super(applet);
        x = 480;
        y = 320;
        r = 50;
        vx = 5.0f;
        vy = 5.0f;
    }

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


}
