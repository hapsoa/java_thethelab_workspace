package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public void settings() {
        size(Constant.APPLET_WIDTH, Constant.APPLET_HEIGHT);
    }

    private List<View> views = new ArrayList<>();
    private Map map = new Map(this, 10, 10, 20);
    static PImage bombImage;

    public void setup() {
        views.add(map);
        bombImage = loadImage("bomb.png");
    }

    public void draw() {
        for (View view : views) {
            view.render();
        }

    }

}
