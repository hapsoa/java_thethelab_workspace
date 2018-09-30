package io.thethelab;

import processing.core.PApplet;

public class Rect implements GivingUtil {

    private PApplet pApplet;
    private int x, y;
    private int width, height;

    public Rect(PApplet pApplet) {
        this.pApplet = pApplet;
        x = (int)(Math.random()*Constant.APPLET_WIDTH);
        y = (int)(Math.random()*Constant.APPLET_HEIGHT);
        width = (int)(Math.random()*30 + 15);
        height = (int)(Math.random()*30 + 15);
    }

    public void update() {
        if (isSelected()) {
            setPosition(pApplet.mouseX, pApplet.mouseY);
        }

    }

    public void render() {
        pApplet.fill(255);
        pApplet.rect(x, y, width, height);
    }

    @Override
    public void setPosition(int mouseX, int mouseY) {

    }

    @Override
    public boolean isSelected() {
        return (isCollision(pApplet.mouseX, pApplet.mouseY)
                && pApplet.mousePressed);
    }

    @Override
    public boolean isCollision(int mouseX, int mouseY) {
        return Math.abs(x - mouseX) < width/2;
    }

}
