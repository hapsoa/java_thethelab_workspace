package io.thethelab;

import processing.core.PApplet;

public class Rect extends ExampleBaseObject{
    private float pastMouseX;

    public Rect(PApplet p, float pastMouseX) {
        super(p);
        x = pApplet.mouseX;
        y = pApplet.mouseY;
        vx = pApplet.mouseX - pastMouseX;
        ax = 1.0f;
    }

    @Override
    public void update() {
        vx *= ax;
        ax *= 0.99f;
        x += vx;
    }

    @Override
    public void render() {
        pApplet.rect(x-5, y-5, 5, 5);
    }
}
