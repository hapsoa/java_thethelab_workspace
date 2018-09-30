package io.thethelab;

import processing.core.PImage;

import java.util.HashMap;
import java.util.Map;

public class ResourceManagers {
    static private Map<Integer, PImage> images = new HashMap<>();

    static void init() {
//        images.put();
    }

    static PImage loadImage(Integer imageKey) {
        return images.get(imageKey);
    }
}
