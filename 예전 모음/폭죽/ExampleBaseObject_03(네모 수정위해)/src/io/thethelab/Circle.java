package io.thethelab;

import processing.core.PApplet;

public class Circle extends ExampleBaseObject {

    public Circle(PApplet p) {
        super(p);
        x = pApplet.mouseX;
        y = pApplet.mouseY;
        vy = -5.0f;
        ay = -0.1f;
    }

    @Override
    public void update() {
        y += vy;
        vy += ay;
    }

    @Override
    public void render() {
        pApplet.ellipse(x, y, 10, 10);
    }
}
