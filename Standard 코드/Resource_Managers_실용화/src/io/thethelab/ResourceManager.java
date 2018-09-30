package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class ResourceManager {


    private static HashMap<Integer, PImage> images = new HashMap<>();

//    private static boolean loaded = false;
//
//    public static void init(PApplet pApplet) {
//
//        images.put(
//                ResourceManager.NUMBER_0,
//                pApplet.loadImage("./images/zero.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_1,
//                pApplet.loadImage("./images/one.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_2,
//                pApplet.loadImage("./images/two.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_3,
//                pApplet.loadImage("./images/three.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_4,
//                pApplet.loadImage("./images/four.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_5,
//                pApplet.loadImage("./images/five.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_6,
//                pApplet.loadImage("./images/six.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_7,
//                pApplet.loadImage("./images/seven.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_8,
//                pApplet.loadImage("./images/eight.png")
//        );
//
//        images.put(
//                ResourceManager.NUMBER_9,
//                pApplet.loadImage("./images/nine.png")
//        );
//    }

    public static void putImageToMap(Integer keyNumber, PImage image) {

        if (!images.containsKey(keyNumber)) { // 기존의 키가 없으면
            images.put(keyNumber, image);
        }
        else {
            throw new IllegalStateException("키 중복되었습니다. 키 중복을 피해주세요");
        }

    }

    public static PImage loadImage(Integer imageName) {
        return images.get(imageName);
    }

}
