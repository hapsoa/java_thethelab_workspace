package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Window extends PApplet {

    public void settings() {
        size(800, 600);
    }

    ArrayList<PImage> images;
    int tick = 0;
    int i = 0;

    public void setup() {
        SpriteManager.loadImage(this, 0,
                "./images/character_sprites.png", 3, 4,
                32, 32, 3);

        images = SpriteManager.getImages(0);

    }

    public void draw() {
        background(0);

        tick++;

        if (tick % 10 == 0) {
            i = (i + 1) % 3;
        }

        image(images.get(i), 30, 30);
    }

}
