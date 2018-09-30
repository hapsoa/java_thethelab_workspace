package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    PApplet pApplet;
    int x, y;
    int width, height;
    boolean deleteTrue;

    boolean checkBool;

    public View(PApplet pApplet, int x, int y, int width, int height) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        deleteTrue = false;
        checkBool = true;
    }

    abstract void render();
    abstract void update();
    abstract void mouseClicked();

    boolean isCollision(int mouseX, int mouseY) {
        if (mouseX > x && mouseX < x + width &&
                mouseY > y && mouseY < y + height)
            return true;
        else
            return false;
    }


}
