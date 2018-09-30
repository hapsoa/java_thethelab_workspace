package io.thethelab;

import processing.core.PApplet;

abstract public class View extends PApplet {

    protected PApplet pApplet;
    protected int x, y;
    protected int width, height;

    public View(PApplet pApplet, int x, int y, int width, int height) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void mouseClicked(int x, int y) {
        if (isCollision(x, y)) {
            clicked();
        }
    }

    private boolean isCollision(int x, int y) {
        return (x > this.x && x < this.x + width &&
                y > this.y && y < this.y + height);
    }

    abstract public void clicked();
    abstract public void render();


}
