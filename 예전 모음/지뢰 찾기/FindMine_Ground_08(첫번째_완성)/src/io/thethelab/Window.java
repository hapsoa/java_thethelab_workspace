package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    private int sizeX = 15;
    private int sizeY = 15;
    int numOfBomb = 30;

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
        System.out.println("hello");

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
