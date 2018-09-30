package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    abstract public void update();
    abstract public void render(PApplet pApplet);
    abstract public void mouseClicked();

}
