package io.thethelab;

import processing.core.PApplet;

public class Block extends View {
    private int x, y;
    private int property;

    Block(PApplet pApplet, int x, int y, int property) {
        super(pApplet);
        this.x = x;
        this.y = y;
        this.property = property;
    }

    @Override
    public void render() {

        pApplet.fill(150);
        pApplet.rect(x - 15, y - 15, 30, 30);

        if (property == Constant.BOMB) {
            pApplet.image(Window.bombImage, x-6, y-6);
        }
        else {
            pApplet.fill(255);
            pApplet.text(property, x, y);
        }
    }
}
