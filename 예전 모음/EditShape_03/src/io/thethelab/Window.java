package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public class Window extends PApplet {

    public void settings() {
        size(960, 640);
    }

    ArrayList<View> views = new ArrayList<>();
    View bar;
    View rect;
    View circle;

    public void setup() {
        background(255);
        bar = new Bar(this);

        views.add(bar);
    }

    public void draw() {
        background(255);

        for (View view : views) {
            view.update();
            view.render();
        }

        if () {
            rect = new Rect();
            views.add(rect);
        }

    }

}
