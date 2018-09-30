package io.thethelab;

import processing.core.PApplet;

public class Circle extends View {

    private int x, y;
    private int raidus;
    private int r, g, b;

    public Circle(PApplet pApplet, int x, int y, int radius, int r, int g, int b) {
        super(pApplet);
        this.x = x;
        this.y = y;
        this.raidus = radius;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        pApplet.fill(r, g, b);
        pApplet.ellipse(x, y, raidus, raidus);
    }

    @Override
    public void mouseClicked() {

    }

    @Override
    void onClick() {

    }

    @Override
    public boolean collided(int mouseX, int mouseY) {
        return false;
    }
}
