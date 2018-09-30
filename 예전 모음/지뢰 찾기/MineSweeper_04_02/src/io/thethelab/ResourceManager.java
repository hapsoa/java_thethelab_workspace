package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class ResourceManager {

    public static final int MINE = 10;

    public static final Integer NUMBER_0 = 0;
    public static final Integer NUMBER_1 = 1;
    public static final Integer NUMBER_2 = 2;

    private static HashMap<Integer, PImage> images = new HashMap<>();

    private static boolean loaded = false;

    public static void init(PApplet pApplet) {
        images.put(
                ResourceManager.NUMBER_1,
                pApplet.loadImage("./images/one.png")
        );

        images.put(
                ResourceManager.NUMBER_2,
                pApplet.loadImage("./images/two.png")
        );
    }

}
