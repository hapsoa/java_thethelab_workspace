package io.thethelab;

import processing.core.PApplet;

import java.util.LinkedList;

public class RectObject extends View {

    int index;
    boolean clicked = false;

    public RectObject(PApplet pApplet, int x, int y, int width, int height, int index) {
        super(pApplet, x, y, width, height);
        this.index = index;
    }

    @Override
    public void render() {
        pApplet.fill(0, 255, 0);
        pApplet.rect(x, y, width, height);

        System.out.println(index);
    }


    public void mouseClicked() {
        if (isCollision(pApplet.mouseX, pApplet.mouseY)) {
            clicked = true;
        }
    }
}
