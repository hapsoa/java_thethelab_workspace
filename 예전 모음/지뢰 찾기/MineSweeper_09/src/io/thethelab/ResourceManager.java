package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class ResourceManager {

    private static HashMap<String, PImage> images = new HashMap<>();

    static String MINE = "BOMB";
    static String FLAG = "FLAG";
    static String SMILE_HAPPY = "SMILE HAPPY";
    static String SMILE_WONDER = "SMILE WONDER";
    static String SMILE_SUNGLASS = "SMILE SUNGLASS";
    static String SMILE_DEAD = "SMILE DEAD";


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

        images.put(
                SMILE_HAPPY,
                pApplet.loadImage("./images/happy.png")
        );

        images.put(
                SMILE_WONDER,
                pApplet.loadImage("./images/surprised.png")
        );
        images.put(
                SMILE_SUNGLASS,
                pApplet.loadImage("./images/cool.png")
        );
        images.put(
                SMILE_DEAD,
                pApplet.loadImage("./images/nervous.png")
        );

    }

    public static PImage loadImage(String imageName) {
        return images.get(imageName);
    }



}
