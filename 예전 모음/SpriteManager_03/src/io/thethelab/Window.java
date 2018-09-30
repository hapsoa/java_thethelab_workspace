package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Window extends PApplet {

    public void settings() {
        size(800, 600);
    }

    private ArrayList<PImage> images;
    private int tick = 0;
    private int i = 0;
    private int[] indices = {0, 1, 2, 1};
    private boolean isLoop = true;
    private int spriteCount = 3;

    public void setup() {
//        SpriteManager.loadImage(this, 0,
//                "./images/character_sprites.png", 3, 4,
//                32, 32, 3);

//        SpriteManager.loadImage(this, 0,
//                "./images/character_sprites.png",
//                32, 32, 3, indices);

        SpriteManager.loadImage(this, 0,
                "./images/character_sprites.png", 3, 4,
                32, 32, spriteCount, isLoop);

        images = SpriteManager.getImages(0);

    }

    public void draw() {
        background(0);

        tick++;

        if (tick % 20 == 0) {
            if (isLoop) {
                i = (i+1) % (spriteCount*2 -2);
            }
            else {
                i = (i + 1) % spriteCount;
            }
        }

        image(images.get(i), 30, 30);
    }

}
