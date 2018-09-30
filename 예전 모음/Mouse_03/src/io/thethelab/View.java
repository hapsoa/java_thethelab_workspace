package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    PApplet pApplet;
    int x, y;
    View draggingObject;

    View(PApplet pApplet, int x, int y) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
    }

    public void update() {

            setPosition(pApplet.mouseX-pApplet.pmouseX, pApplet.mouseY-pApplet.pmouseY);

    }

    abstract public void render();

    abstract public boolean isSelected(int mouseX, int mouseY);

    private void setPosition(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
