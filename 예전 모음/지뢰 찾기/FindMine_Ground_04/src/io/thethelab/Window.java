package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    private int sizeX = 12;
    private int sizeY = 12;

    public void settings() {

        size(sizeX * Constant.BLOCK_SIZE,
                sizeX * Constant.BLOCK_SIZE);
    }

    private List<View> views = new ArrayList<>();
    public Map map = new Map(this, sizeX, sizeY, 3);
    private Curtain curtain = new Curtain(this, sizeX, sizeY);

    static PImage bombImage;
    static PImage flagImage;

    public void setup() {
        views.add(map);
        views.add(curtain);
        bombImage = loadImage("bomb.png");
        flagImage = loadImage("flag.png");
    }

    public void draw() {
        for (View view : views) {
            view.render();
        }

    }

    public void keyReleased() {
        for (View view : views) {
            view.update();
        }
    }

}
