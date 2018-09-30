package io.thethelab;

import processing.core.PApplet;

public class Rect extends View {

    private int x, y;
    private int height, width;
    private int r, g, b;

    public Rect(PApplet pApplet, int x, int y, int width, int height, int r, int g, int b) {
        super(pApplet);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void update() {
        mouseClicked();
    }

    @Override
    public void render() {
        pApplet.fill(r, g, b);
        pApplet.rect(x, y, width, height);
    }

    @Override
    public void mouseClicked() {
        super.mouseClicked();
    }

    @Override
    void onClick() {
        if (pApplet.mouseX > 5 && pApplet.mouseX < 35 &&
                pApplet.mouseY > 5 && pApplet.mouseY < 35) {
            pApplet.fill(0);
            pApplet.rect(400, 400, 30, 30);
        }
    }

    @Override
    public boolean collided(int mouseX, int mouseY) {
        if (mouseX > x && mouseX < x + width &&
                mouseY > y && mouseY < y + height) {
            return true;
        }
        else
            return false;
    }

}
