package io.thethelab;

abstract public class View {

    Window pApplet;

    public View(Window pApplet) {
        this.pApplet = pApplet;
    }

    abstract public void standBy();
    abstract public void update();
    abstract public void render();
}
