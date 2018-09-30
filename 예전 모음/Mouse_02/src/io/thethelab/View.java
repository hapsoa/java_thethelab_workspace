package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    PApplet pApplet;
    int x, y;

    public View(PApplet pApplet, int x, int y) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (isSelected(pApplet.mouseX, pApplet.mouseY)) {
            setPosition(pApplet.mouseX, pApplet.mouseY);
        }
    }

    abstract public void render();

    abstract public boolean isSelected(int mouseX, int mouseY);

    public void setPosition(int mouseX, int mouseY) {
        x = mouseX;
        y = mouseY;
    }
}
