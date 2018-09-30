package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

public class Window extends PApplet {

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        ResourceManager.init(this);
    }

    int mineCount;

    int tick = 0;

    public void draw() {
        background(0);
        tick++;
        if (tick % 10 == 0)
            mineCount++;

        PImage oneNumber =
                ResourceManager.loadImage(
                        ResourceManager.NUMBER_0 + mineCount % 10);
        PImage tenNumber =
                ResourceManager.loadImage(
                        ResourceManager.NUMBER_0 + (mineCount / 10) % 10
                );

        image(oneNumber, 50, 10);
        image(tenNumber, 10, 10);

    }
}
