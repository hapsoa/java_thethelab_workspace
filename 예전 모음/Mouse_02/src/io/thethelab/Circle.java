package io.thethelab;

import processing.core.PApplet;

public class Circle extends View {

    int r;

    public Circle(PApplet pApplet, int x, int y, int r) {
        super(pApplet, x, y);
        this.r = r;
    }



    @Override
    public void render() {
        pApplet.fill(255);
        pApplet.ellipse(x, y, r, r);
    }

    @Override
    public boolean isSelected(int mouseX, int mouseY) {
        if (Math.sqrt((mouseX-x)*(mouseX-x) + (mouseY-y)*(mouseY-y)) < r && pApplet.mousePressed) {
            return true;
        }
        else
            return false;
    }

//    public boolean isCollision(int mouseX, int mouseY) {
//
//    }
}
