package io.thethelab;

import processing.core.PApplet;

public class RectObject extends View {

    public RectObject(PApplet pApplet, int x, int y, int width, int height) {
        super(pApplet, x, y, width, height);
    }

    @Override
    public void render() {
        pApplet.fill(0, 255, 0);
        pApplet.rect(x, y, width, height);
    }
}
