package io.thethelab;

import processing.core.PApplet;

abstract public class View  {

    protected PApplet pApplet;
    protected boolean pressed;

    public View(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    abstract public void update();
    abstract public void render();

    public void mouseClicked() {
        if (pApplet.mousePressed) {
            pressed = true;
        } else if (pressed && collided(pApplet.mouseX, pApplet.mouseY)) {
            onClick();
            pressed = false;
        }
    }

    abstract void onClick();

    abstract public boolean collided(int mouseX, int mouseY);



}
