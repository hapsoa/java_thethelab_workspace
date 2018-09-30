package io.thethelab;

import processing.core.PApplet;

public class Button extends View {


    public Button(PApplet pApplet, int x, int y, int width, int height) {
        super(pApplet, x, y, width, height);
    }


    @Override
    public void render() {
        pApplet.fill(255, 70, 255);
        pApplet.rect(x, y, width, height);
    }


}
