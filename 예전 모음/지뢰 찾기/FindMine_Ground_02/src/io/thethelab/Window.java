package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    private int sizeX = 13;
    private int sizeY = 13;

    public void settings() {

        size(sizeX * Constant.BLOCK_SIZE,
                sizeX * Constant.BLOCK_SIZE);
    }

    private List<View> views = new ArrayList<>();
    private Map map = new Map(this, sizeX, sizeY, 20);
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
