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

            if (startY + sizeY > height)
                throw new IllegalArgumentException("요청한 이미지가 기존 이미지를 넘어감");

            if (startX + sizeX > width) {
                startY += sizeY;
                startX = column * sizeX;
            }
        }
        if (sprites.containsKey(state)) {
            throw new IllegalArgumentException("이미 등록된 상태임");
        }
        sprites.put(state, images);
    }








    public static void loadImage(PApplet pApplet,
                                 int state, String path,
                                 int sizeX, int sizeY, int spriteCount,
                                 int[] indices) {

        PImage image = pApplet.loadImage(path);
        int width = image.width;
        int height = image.height;
        int startX;
        int startY;

        ArrayList<PImage> images = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) {
            startX = (indices[i]%spriteCount) * sizeX;
            startY = sizeY * (indices[i]/spriteCount);

            PImage img = image.get(startX, startY, sizeX, sizeY);
            images.add(img);

//            if (startX + sizeX > width) {
//                startY += sizeY;
//                startX = indices[0] * sizeX;
//            }
//            if (startY + sizeY > height)
//                throw new IllegalArgumentException("요청한 이미지가 기존 이미지를 넘어감");
        }

        if (sprites.containsKey(state)) {
            throw new IllegalArgumentException("이미 등록된 상태임");
        }
        sprites.put(state, images);

    }

    public static void loadImage(PApplet pApplet,
                                 int state, String path,
                                 int column, int row, int sizeX, int sizeY,
                                 int spriteCount, boolean isLoop) {

        PImage image = pApplet.loadImage(path);
        int width = image.width;
        int height = image.height;
        int startX = column * sizeX;
        int startY = row * sizeY;

        ArrayList<PImage> images = new ArrayList<>();

        if (isLoop) {
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

            startX -= sizeX*2;

            for (int i = 0; i < spriteCount-2; i++) {
                PImage img = image.get(startX, startY, sizeX, sizeY);
                images.add(img);
                startX -= sizeX;

                if (startX - sizeX < 0) {
                    startY -= sizeY;
                    startX = (column+spriteCount-1) * sizeX;
                }
                if (startY - sizeY < 0)
                    throw new IllegalArgumentException("요청한 이미지가 기존 이미지를 넘어감");
            }
        }
        else {
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
        }



        if (sprites.containsKey(state)) {
            throw new IllegalArgumentException("이미 등록된 상태임");
        }
        sprites.put(state, images);

    }

    public static ArrayList<PImage> getImages(int state) {
        if (!sprites.containsKey(state))
            throw new IllegalArgumentException("state가 없습니다.");

        return sprites.get(state);
    }

    public static PImage getImage(int state, int index) {
        if (!sprites.containsKey(state))
            throw new IllegalArgumentException("state가 없습니다.");

        ArrayList<PImage> images = sprites.get(state);
        return images.get(index % images.size());
    }

}
