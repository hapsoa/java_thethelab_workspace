package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class ResourceManager {

    public static final int MINE = 10;

    public static final Integer NUMBER_0 = 0;
    public static final Integer NUMBER_1 = 1;
    public static final Integer NUMBER_2 = 2;
    public static final Integer NUMBER_3 = 3;
    public static final Integer NUMBER_4 = 4;
    public static final Integer NUMBER_5 = 5;
    public static final Integer NUMBER_6 = 6;
    public static final Integer NUMBER_7 = 7;
    public static final Integer NUMBER_8 = 8;
    public static final Integer NUMBER_9 = 9;

    private static HashMap<Integer, PImage> images = new HashMap<>();

    private static boolean loaded = false;

    public static void init(PApplet pApplet) {

        images.put(
                ResourceManager.NUMBER_0,
                pApplet.loadImage("./images/zero.png")
        );

        images.put(
                ResourceManager.NUMBER_1,
                pApplet.loadImage("./images/one.png")
        );

        images.put(
                ResourceManager.NUMBER_2,
                pApplet.loadImage("./images/two.png")
        );

        images.put(
                ResourceManager.NUMBER_3,
                pApplet.loadImage("./images/three.png")
        );

        images.put(
                ResourceManager.NUMBER_4,
                pApplet.loadImage("./images/four.png")
        );

        images.put(
                ResourceManager.NUMBER_5,
                pApplet.loadImage("./images/five.png")
        );

        images.put(
                ResourceManager.NUMBER_6,
                pApplet.loadImage("./images/six.png")
        );

        images.put(
                ResourceManager.NUMBER_7,
                pApplet.loadImage("./images/seven.png")
        );

        images.put(
                ResourceManager.NUMBER_8,
                pApplet.loadImage("./images/eight.png")
        );

        images.put(
                ResourceManager.NUMBER_9,
                pApplet.loadImage("./images/nine.png")
        );
    }

    public static PImage loadImage(Integer imageName) {
        return images.get(imageName);
    }

}
