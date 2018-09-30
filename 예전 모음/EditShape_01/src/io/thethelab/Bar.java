package io.thethelab;

import processing.core.PApplet;

public class Bar extends View {
    public Bar(PApplet pApplet) {
        super(pApplet);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        drawBar();
        drawRect();
        drawCircle();
    }

    @Override
    public void mouseClicked() {

    }

    private void drawBar() {
        pApplet.fill(100);
        pApplet.rect(0, 0, 960, 40);
    }

    private void drawRect() {
        pApplet.fill(255);
        pApplet.rect(5, 5, 30, 30);
    }

    private void drawCircle() {
        pApplet.fill(255);
        pApplet.ellipse(60, 20, 30, 30);
    }

}
