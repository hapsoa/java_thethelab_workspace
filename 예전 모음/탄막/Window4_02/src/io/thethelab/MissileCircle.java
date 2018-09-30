package io.thethelab;

import processing.core.PApplet;

public class MissileCircle extends Circle {

    public MissileCircle(PApplet applet, float playerX, float playerY, int tick) {
        super(applet);

        if (tick % 4 == 0) {
            x = (float)Math.random() * 960.0f;
            y = -20.0f;
        }
        else if (tick % 4 == 1) {
            x = (float)Math.random() * 960.0f;
            y = 680;
        }
        else if (tick % 4 == 2) {
            x = -20.0f;
            y = (float)Math.random() * 640.0f;
        }
        else if (tick % 4 == 3) {
            x = 980.0f;
            y = (float)Math.random() * 640.0f;
        }

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
