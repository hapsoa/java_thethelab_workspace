package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class ResourceManager {

    private static HashMap<String, PImage> images = new HashMap<>();

    static String MINE = "BOMB";
    static String FLAG = "FLAG";

    private static boolean loaded = false;

    static void init(PApplet pApplet) {
        images.put(
                MINE,
                pApplet.loadImage("./images/bomb.png")
        );

        images.put(
                FLAG,
                pApplet.loadImage("./images/flag.png")
        );

    }

    public static PImage loadIamge(String imageName) {
        return images.get(imageName);
    }



}
