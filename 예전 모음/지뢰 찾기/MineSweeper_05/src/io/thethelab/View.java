package io.thethelab;

import processing.core.PApplet;

abstract class View {
    PApplet pApplet;

    View(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    abstract void update();
    abstract void render();
    abstract void standBy();
}
