package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class ResourceManager {

    private static HashMap<Integer, PImage> images = new HashMap<>();

    static Integer MINE = 11;
    static Integer FLAG = 12;
    static Integer SMILE_HAPPY = 13;
    static Integer SMILE_WONDER = 14;
    static Integer SMILE_SUNGLASS = 15;
    static Integer SMILE_DEAD = 16;

    static final Integer NUMBER_0 = 0;
    static final Integer NUMBER_1 = 1;
    static final Integer NUMBER_2 = 2;
    static final Integer NUMBER_3 = 3;
    static final Integer NUMBER_4 = 4;
    static final Integer NUMBER_5 = 5;
    static final Integer NUMBER_6 = 6;
    static final Integer NUMBER_7 = 7;
    static final Integer NUMBER_8 = 8;
    static final Integer NUMBER_9 = 9;

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

    public static PImage loadImage(Integer imageNum) {
        return images.get(imageNum);
    }



}
