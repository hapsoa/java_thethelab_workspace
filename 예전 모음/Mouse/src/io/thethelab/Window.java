package io.thethelab;

import processing.core.PApplet;

public class Window extends PApplet {

    private final int RectCount = 10;
    Rect[] rects = new Rect[RectCount];

    public void settings() {
        size(800, 600);

        for (int i = 0; i < RectCount; i++) {
            rects[i] = new Rect(this,
                    (int)(Math.random() * 800),
                    (int)(Math.random() * 600),
                    50,
                    50);

        }
    }

    public void setup() {

    }

    private Rect clickedRect = null;

    public void draw() {
        background(200);

        if(mousePressed) {
            for (Rect r : rects) {
                if (r.isCollision(mouseX, mouseY)) {
                    clickedRect = r;
                }
            }
        } else clickedRect = null;

        if(clickedRect != null) {
            clickedRect.setPosition(mouseX,mouseY);

        }

        for (Rect r : rects) r.render();

    }
}
