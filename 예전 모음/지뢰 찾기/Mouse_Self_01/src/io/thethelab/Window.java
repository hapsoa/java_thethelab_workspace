package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public void settings() {
        size(Constant.APPLET_WIDTH, Constant.APPLET_HEIGHT);
    }

    private List<Rect> rects = new ArrayList<>();

    public void setup() {
        for (int i = 0;i < 10; i++)
            rects.add(new Rect(this));

    }

    public void draw() {
        background(150);

        for (Rect rect : rects) {
            rect.render();
        }
    }
}
