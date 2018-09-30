package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    private int sizeX = 12;
    private int sizeY = 12;
    int numOfBomb = 20;

    public void settings() {

        size(sizeX * Constant.BLOCK_SIZE,
                sizeX * Constant.BLOCK_SIZE);
    }

    private List<View> views = new ArrayList<>();
     Map map = new Map(this, sizeX, sizeY, numOfBomb);
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
