package io.thethelab;

import processing.core.PApplet;

public class Circle extends ExampleBaseObject {

    public Circle(PApplet p) {
        super(p);
//        x = pApplet.mouseX;
//        y = pApplet.mouseY;
        vx = (float)Math.random() * 1.0f - 0.5f;
        vy = (float)Math.random() * 1.0f - 0.5f;
        ax = (float)Math.random() * 1.0f - 0.5f;
        ay = (float)Math.random() * 1.0f - 0.5f;
    }

    @Override
    public void update() {
        x += vx;
        y += vy;

        vx += ax;
        vy += ay;

        ax += Math.random() * 1.0f - 0.5f;
        ay += Math.random() * 1.0f - 0.5f;
    }

    @Override
    public void render() {
        pApplet.ellipse(x, y, 10, 10);
    }


}
