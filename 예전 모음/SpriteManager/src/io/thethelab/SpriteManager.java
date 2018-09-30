package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;

public class SpriteManager {
    private SpriteManager() {

    }

    private static HashMap<Integer, ArrayList<PImage>> sprites = new HashMap<>();

    public static void loadImage(
            PApplet pApplet,
            int state, String path,
            int column, int row, int sizeX, int sizeY, int spriteCount) {

        PImage image = pApplet.loadImage(path);
        int width = image.width;
        int height = image.height;
        int startX = column * sizeX;
        int startY = row * sizeY;

        ArrayList<PImage> images = new ArrayList<>();
        for (int i = 0; i < spriteCount; i++) {
            PImage img = image.get(startX, startY, sizeX, sizeY);
            images.add(img);
            startX += sizeX;

            if (startX + sizeX > width) {
                startY += sizeY;
                startX = column * sizeX;
            }
            if (startY + sizeY > height)
                throw new IllegalArgumentException("요청한 이미지가 기존 이미지를 넘어감");
        }
        if (sprites.containsKey(state)) {
            throw new IllegalArgumentException("이미 등록된 상태임");
        }
        sprites.put(state, images);
    }

    public static ArrayList<PImage> getImages(int state) {
        if (!sprites.containsKey(state))
            throw new IllegalArgumentException("");

        return sprites.get(state);
    }

    public static PImage getImage(int state, int index) {
        if (!sprites.containsKey(state))
            throw new IllegalArgumentException("");

        ArrayList<PImage> images = sprites.get(state);
        return images.get(index % images.size());
    }

}
