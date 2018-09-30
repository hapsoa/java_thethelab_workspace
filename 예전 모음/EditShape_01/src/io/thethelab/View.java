package io.thethelab;

import processing.core.PApplet;

abstract public class View  {

    protected PApplet pApplet;

    public View(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    abstract public void update();
    abstract public void render();

    abstract public void mouseClicked();


}
