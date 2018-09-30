package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    static Map<Integer, PImage> images = new HashMap<>();

    static final int DOWN_0 = 0;
    static final int DOWN_1 = 1;
    static final int DOWN_2 = 2;

    static final int LEFT_0 = 3;
    static final int LEFT_1 = 4;
    static final int LEFT_2 = 5;

    static final int RIGHT_0 = 6;
    static final int RIGHT_1 = 7;
    static final int RIGHT_2 = 8;

    static final int UP_0 = 9;
    static final int UP_1 = 10;
    static final int UP_2 = 11;

    static void init(PApplet pApplet) {

//        PImage mountains = loadImage("rockies.jpg");
//        background(mountains);
//        PImage newMountains = mountains.get(50, 0, 50, 100);
//        image(newMountains, 0, 0);

        PImage characterSprite =
                pApplet.loadImage("./images/character_sprites.png");

//        pApplet.background(characterSprite);
//
//        PImage newImage = characterSprite.get(0, 0, 25, 32);
//
//        pApplet.image(newImage, 0, 0);


        images.put(
                DOWN_0,
                characterSprite.get(0, 0, 25, 32)
        );
        images.put(
                DOWN_1,
                characterSprite.get(30, 0, 25, 32)
        );
        images.put(
                DOWN_2,
                characterSprite.get(60, 0, 25, 32)
        );

        images.put(
                LEFT_0,
                characterSprite.get(0, 40, 25, 32)
        );
        images.put(
                LEFT_1,
                characterSprite.get(30, 40, 25, 32)
        );
        images.put(
                LEFT_2,
                characterSprite.get(60, 40, 25, 32)
        );

        images.put(
                RIGHT_0,
                characterSprite.get(0, 80, 25, 32)
        );
        images.put(
                RIGHT_1,
                characterSprite.get(30, 80, 25, 32)
        );
        images.put(
                RIGHT_2,
                characterSprite.get(60, 80, 25, 32)
        );

        images.put(
                UP_0,
                characterSprite.get(0, 120, 25, 32)
        );
        images.put(
                UP_1,
                characterSprite.get(30, 120, 25, 32)
        );
        images.put(
                UP_2,
                characterSprite.get(60, 120, 25, 32)
        );
    }

    //loadImage()
    static PImage loadImage(int imageCode) {
        return images.get(imageCode);
    }
}
