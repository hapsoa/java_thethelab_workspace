package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {
    public void settings() {
        size(384, 256);
    }

    Character character = new Character(this);
    private List<View> views = new ArrayList<>();

    public void setup() {
        ResourceManager.init(this);
        views.add(character);
    }

    public void draw() {

        background(0);

        for (View view : views) {
            view.play();
            view.render();
        }




    }
}
