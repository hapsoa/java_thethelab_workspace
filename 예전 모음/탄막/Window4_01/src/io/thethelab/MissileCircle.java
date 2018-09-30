package io.thethelab;

import processing.core.PApplet;

public class MissileCircle extends Circle {

    public MissileCircle(PApplet applet, float playerX, float playerY) {
        super(applet);

        x = (float)Math.random() * 960.0f;
        y = -20.0f;
        r = 20.0f;
        vx = 3 * (playerX - this.x) /
                (float)Math.sqrt((playerX-this.x)*(playerX-this.x)
                        + (playerY-this.y)*(playerY-this.y));
        vy = 3 * (playerY - this.y) /
                (float)Math.sqrt((playerX-this.x)*(playerX-this.x)
                        + (playerY-this.y)*(playerY-this.y));
    }

    public void update() {
        x += vx;
        y += vy;
    }

}
