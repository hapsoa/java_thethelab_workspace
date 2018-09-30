package io.thethelab;

import processing.core.PApplet;

public class Rect extends View {

    private int width, height;

    Rect(PApplet pApplet) {
        super(pApplet, (int)(Math.random()*Constant.APPLET_WIDTH),
                (int)(Math.random()*Constant.APPLET_HEIGHT));
        this.width = (int)(Math.random()*30)+15;
        this.height = (int)(Math.random()*30)+15;
    }

    @Override
    public void render() {
        pApplet.fill(255);
        pApplet.rect(x- width/2, y- height/2, width, height);
    }

    @Override
    public boolean isSelected(int mouseX, int mouseY) {
        return (Math.abs(x-mouseX) < width/2 && Math.abs(y-mouseY) < height/2);

    }
}
