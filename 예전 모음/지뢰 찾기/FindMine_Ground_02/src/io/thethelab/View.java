package io.thethelab;

import processing.core.PApplet;

abstract public class View {

    PApplet pApplet;

    View(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    abstract public void render();
}
