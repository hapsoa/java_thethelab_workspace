package io.thethelab;

import processing.core.PApplet;

import java.util.LinkedList;

public class RectObject extends View {

    int index;
    boolean clicked = false;
    int number;

    public RectObject(PApplet pApplet, int x, int y, int width, int height, int index, int number) {
        super(pApplet, x, y, width, height);
        this.index = index;
        this.number = number;
    }

    @Override
    public void render() {
        pApplet.fill(0, 255, 0);
        pApplet.rect(x, y, width, height);
        pApplet.fill(255);
        pApplet.text(number, x, y);

    }


    public void mouseClicked() {
        if (isCollision(pApplet.mouseX, pApplet.mouseY)) {
            clicked = true;
        }
    }
}
