package io.thethelab;

import processing.core.PApplet;

public class Bar extends View {

    private Rect barRect = new Rect(pApplet, 0, 0,
            960, 40, 100, 100, 100);
    private Rect rect = new Rect(pApplet, 5, 5,
            30, 30, 255, 255, 255);
    private Circle circle = new Circle(pApplet, 60, 20,
            30, 255, 255, 255);


    public Bar(PApplet pApplet) {
        super(pApplet);
    }

    @Override
    public void update() {
        barRect.update();
        rect.update();
        circle.update();
    }

    @Override
    public void render() {
        barRect.render();
        rect.render();
        circle.render();
    }

    @Override
    void onClick() {

    }

    @Override
    public boolean collided(int mouseX, int mouseY) {

        return false;
    }



}
