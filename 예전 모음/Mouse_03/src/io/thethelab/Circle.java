package io.thethelab;

import processing.core.PApplet;

public class Circle extends View {

    private int r;

    Circle(PApplet pApplet) {
        super(pApplet, (int)(Math.random()*Constant.APPLET_WIDTH),
                (int)(Math.random()*Constant.APPLET_HEIGHT));
        this.r = (int)(Math.random()*30)+10;
    }


    @Override
    public void render() {
        pApplet.fill(255);
        pApplet.ellipse(x, y, r, r);
    }

    @Override
    public boolean isSelected(int mouseX, int mouseY) {
        return Math.sqrt((mouseX-x)*(mouseX-x) + (mouseY-y)*(mouseY-y)) < r && pApplet.mousePressed;

    }


}
