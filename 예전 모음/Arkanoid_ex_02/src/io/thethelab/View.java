package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    PApplet pApplet;
    Vector2 vector2;

    public View(PApplet pApplet) {
        this.pApplet = pApplet;
        vector2 = new Vector2();
    }

    abstract public void standBy();
    abstract public void update();
    abstract public void render();


}
