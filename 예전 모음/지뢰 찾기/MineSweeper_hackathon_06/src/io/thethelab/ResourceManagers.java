package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;
import java.util.Map;

public class ResourceManagers {
    static private Map<Integer, PImage> images = new HashMap<>();

    static final int DIGITAL_ZERO = 0;
    static final int DIGITAL_ONE = 1;
    static final int DIGITAL_TWO = 2;
    static final int DIGITAL_THREE = 3;
    static final int DIGITAL_FOUR = 4;
    static final int DIGITAL_FIVE = 5;
    static final int DIGITAL_SIX = 6;
    static final int DIGITAL_SEVEN = 7;
    static final int DIGITAL_EIGHT = 8;
    static final int DIGITAL_NINE = 9;

    static final int MAP_NUMBER_ONE = 31;
    static final int MAP_NUMBER_TWO = 32;
    static final int MAP_NUMBER_THREE = 33;
    static final int MAP_NUMBER_FOUR = 34;
    static final int MAP_NUMBER_FIVE = 35;
    static final int MAP_NUMBER_SIX = 36;
    static final int MAP_NUMBER_SEVEN = 37;
    static final int MAP_NUMBER_EIGHT = 38;
    static final int MINE = 39;

    static final int FLAG = 11;

    static final int SMILE = 21;
    static final int SMILE_WONDER = 22;
    static final int SMILE_DEAD = 23;
    static final int SMILE_SUNGLASS = 24;

    static void init(PApplet pApplet) {
        images.put(
                DIGITAL_ZERO,
                pApplet.loadImage("./images/digital_zero.png")
        );
        images.put(
                DIGITAL_ONE,
                pApplet.loadImage("./images/digital_one.png")
        );
        images.put(
                DIGITAL_TWO,
                pApplet.loadImage("./images/digital_two.png")
        );
        images.put(
                DIGITAL_THREE,
                pApplet.loadImage("./images/digital_three.png")
        );
        images.put(
                DIGITAL_FOUR,
                pApplet.loadImage("./images/digital_four.png")
        );
        images.put(
                DIGITAL_FIVE,
                pApplet.loadImage("./images/digital_five.png")
        );
        images.put(
                DIGITAL_SIX,
                pApplet.loadImage("./images/digital_six.png")
        );
        images.put(
                DIGITAL_SEVEN,
                pApplet.loadImage("./images/digital_seven.png")
        );
        images.put(
                DIGITAL_EIGHT,
                pApplet.loadImage("./images/digital_eight.png")
        );
        images.put(
                DIGITAL_NINE,
                pApplet.loadImage("./images/digital_nine.png")
        );


        images.put(
                MAP_NUMBER_ONE,
                pApplet.loadImage("./images/map_number_one.png")
                );
        images.put(
                MAP_NUMBER_TWO,
                pApplet.loadImage("./images/map_number_two.png")
        );
        images.put(
                MAP_NUMBER_THREE,
                pApplet.loadImage("./images/map_number_three.png")
        );
        images.put(
                MAP_NUMBER_FOUR,
                pApplet.loadImage("./images/map_number_four.png")
        );
        images.put(
                MAP_NUMBER_FIVE,
                pApplet.loadImage("./images/map_number_five.png")
        );
        images.put(
                MAP_NUMBER_SIX,
                pApplet.loadImage("./images/map_number_six.png")
        );
        images.put(
                MAP_NUMBER_SEVEN,
                pApplet.loadImage("./images/map_number_seven.png")
        );
        images.put(
                MAP_NUMBER_EIGHT,
                pApplet.loadImage("./images/map_number_eight.png")
        );
        images.put(
                MINE,
                pApplet.loadImage("./images/bomb.png")
        );
        images.put(
                FLAG,
                pApplet.loadImage("./images/flag.png")
        );
        images.put(
                SMILE,
                pApplet.loadImage("./images/happy.png")
        );
        images.put(
                SMILE_WONDER,
                pApplet.loadImage("./images/surprised.png")
        );
        images.put(
                SMILE_DEAD,
                pApplet.loadImage("./images/nervous.png")
        );
        images.put(
                SMILE_SUNGLASS,
                pApplet.loadImage("./images/cool.png")
        );
    }

    static PImage loadImage(Integer imageKey) {
        return images.get(imageKey);
    }
}
