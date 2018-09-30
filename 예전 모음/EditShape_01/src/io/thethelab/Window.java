package io.thethelab;

import processing.core.PApplet;

public class Window extends PApplet {

    public void settings() {
        size(960, 640);
    }

    View bar;

    public void setup() {
        background(255);
        bar = new Bar(this);
    }

    public void draw() {
        background(255);

        bar.render();
    }

}
