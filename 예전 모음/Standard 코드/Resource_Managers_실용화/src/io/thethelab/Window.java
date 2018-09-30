package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

public class Window extends PApplet {

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

    public void settings() {
        size(600, 600);
    }

    public void setup() {

        ResourceManager.putImageToMap(NUMBER_0, loadImage("./images/zero.png"));
        ResourceManager.putImageToMap(NUMBER_1, loadImage("./images/one.png"));
        ResourceManager.putImageToMap(NUMBER_2, loadImage("./images/two.png"));
        ResourceManager.putImageToMap(NUMBER_3, loadImage("./images/three.png"));
        ResourceManager.putImageToMap(NUMBER_4, loadImage("./images/four.png"));
        ResourceManager.putImageToMap(NUMBER_5, loadImage("./images/five.png"));
        ResourceManager.putImageToMap(NUMBER_6, loadImage("./images/six.png"));
        ResourceManager.putImageToMap(NUMBER_7, loadImage("./images/seven.png"));
        ResourceManager.putImageToMap(NUMBER_8, loadImage("./images/eight.png"));
        ResourceManager.putImageToMap(NUMBER_9, loadImage("./images/nine.png"));

    }

    int count;

    int tick = 0;

    public void draw() {
        background(0);
        tick++;
        if (tick % 10 == 0)
            count++;

        PImage oneNumber =
                ResourceManager.loadImage(
                        NUMBER_0 + count % 10);
        PImage tenNumber =
                ResourceManager.loadImage(
                        NUMBER_0 + (count / 10) % 10
                );

        image(oneNumber, 50, 10);
        image(tenNumber, 10, 10);

    }
}
