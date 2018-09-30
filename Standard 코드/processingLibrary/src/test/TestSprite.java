package test;

import Manager.SpriteManager;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class TestSprite extends PApplet {

    private ArrayList<PImage> images;
    private PImage image;
    private ArrayList<PImage> images2;
    private PImage barImage;
    private int tick = 0;
    private int i = 0;
    private int[] indices = {0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 0};
    private boolean isLoop = true;
    private int spriteCount = 9;


    public static void main(String[] args) {
        TestSprite.main("test.TestSprite");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        SpriteManager.putImages(this, 0, "./images/ITEM_PLAYER.png", 0, 0
                , 0, 0, 80, 44, 8);

        images = SpriteManager.getImages(0);

        SpriteManager.putImage(this, 1, "./images/BACKGROUND.png");


        image = SpriteManager.getImage(1);

        SpriteManager.putImages(this, 2, "./images/ITEM_SLOW.png", 0, 0,
                80, 44, 8, indices);


        images.addAll(SpriteManager.getImages(2));


        SpriteManager.putImages(this, 3, "./images/ITEM_LASER.png", 0, 0,
                0, 0, 80, 44, 8, true);

        images2 = SpriteManager.getImages(3);

        SpriteManager.putImages(this, 4, "./images/BasicArkanoidPack.png", 153,81,
                0,0,128, 25, 1);
        barImage = SpriteManager.getImage(4);

    }

    private int j = 0;

    public void draw() {
        background(0);

        tick++;
        if (tick % 20 == 0) {
            i = (i + 1) % (indices.length + 8);
            j = (j + 1) % 14;

        }

        image(images.get(i), 30, 30);
        image(image, 200, 30);
        image(images2.get(j), 200, 200);
        image(barImage, 400, 400);
    }

}
