package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    abstract public void update(PApplet pApplet);
    abstract public void render(PApplet pApplet);
    abstract public void mouseClicked(PApplet pApplet);

}
