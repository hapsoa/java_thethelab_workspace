package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    PApplet pApplet;
    int x, y;
    int width, height;

    public View(PApplet pApplet, int x, int y, int width, int height) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    abstract void render();
    abstract void update();
    abstract void mouseClicked();
    abstract void mousePressed();




    boolean isCollision(int mouseX, int mouseY) {
        if (mouseX > x && mouseX < x + width &&
                mouseY > y && mouseY < y + height)
            return true;
        else
            return false;
    }
}
