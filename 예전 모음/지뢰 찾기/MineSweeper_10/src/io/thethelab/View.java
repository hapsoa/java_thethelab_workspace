package io.thethelab;

abstract class View {
    Window pApplet;

    View(Window pApplet) {
        this.pApplet = pApplet;
    }

    abstract void update();
    abstract void render();
    abstract void standBy();
}
