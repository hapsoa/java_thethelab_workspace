package io.thethelab;

import processing.core.PApplet;

public class Rect {
    private PApplet pApplet;
    private int x, y;
    private int width, height;

    public Rect(PApplet pApplet, int x, int y, int width, int height) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render() {
        pApplet.fill(255);
        pApplet.rect(x - width/2, y - height/2, width, height);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    private static Rect clickedRect;


    public void update() {

        if (isCollision(pApplet.mouseX, pApplet.mouseY) &&
                pApplet.mousePressed &&
                clickedRect == null) {
            clickedRect = this;

        }

        if (clickedRect == this && pApplet.mousePressed ) {
            x = pApplet.mouseX;
            y = pApplet.mouseY;
        }
    }



    public boolean isCollision(int mouseX, int mouseY) {
        if (Math.abs(x - mouseX) < width/2 &&
                Math.abs(y - mouseY) < height/2) {
            return true;
        } else
            return false;
    }
}
