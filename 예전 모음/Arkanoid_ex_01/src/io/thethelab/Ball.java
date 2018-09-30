package io.thethelab;

import processing.core.PApplet;

public class Ball extends View {

    int radius;

    public Ball(PApplet pApplet) {
        super(pApplet);
        vector2.x = pApplet.mouseX;
        vector2.y = pApplet.mouseY;
        radius = 5;
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        vector2.x = pApplet.mouseX;
        vector2.y = pApplet.mouseY;

        pApplet.fill(0);
        pApplet.ellipse(vector2.x, vector2.y, radius*2, radius*2);

    }
}
