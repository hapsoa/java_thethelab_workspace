package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    PApplet pApplet;

    public View(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    abstract void update();
    abstract void render();
    abstract void standBy();
}
