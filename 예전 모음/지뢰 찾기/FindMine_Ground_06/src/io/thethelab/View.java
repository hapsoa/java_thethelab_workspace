package io.thethelab;

abstract public class View {

    Window pApplet;

    View(Window pApplet) {
        this.pApplet = pApplet;
    }

    abstract public void update();
    abstract public void render();
}
