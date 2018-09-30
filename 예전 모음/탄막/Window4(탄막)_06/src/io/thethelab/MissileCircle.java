package io.thethelab;

import processing.core.PApplet;

public class MissileCircle extends Circle {

    public MissileCircle(PApplet applet, float playerX, float playerY) {
        super(applet);

        if ((int)(Math.random()*4) == 0) {
            x = (float)Math.random() * 960.0f;
            y = -20.0f;
        }
        else if ((int)(Math.random()*4) == 1) {
            x = (float)Math.random() * 960.0f;
            y = 680;
        }
        else if ((int)(Math.random()*4) == 2) {
            x = -20.0f;
            y = (float)Math.random() * 640.0f;
        }
        else if ((int)(Math.random()*4) == 3) {
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

    public MissileCircle(PApplet applet, float playerX, float playerY,
                         float missileX, float missileY) {
        super(applet);
        x = missileX;
        y = missileY;

        r = 20.0f;
        vx = 3 * (playerX - this.x) /
                (float)Math.sqrt((playerX-this.x)*(playerX-this.x)
                        + (playerY-this.y)*(playerY-this.y));
        vy = 3 * (playerY - this.y) /
                (float)Math.sqrt((playerX-this.x)*(playerX-this.x)
                        + (playerY-this.y)*(playerY-this.y));
    }

    public void update() {
        if (vx < 6)
            vx *= 1.01f;
        if (vy < 6)
            vy *= 1.01f;

        x += vx;
        y += vy;
    }

}
