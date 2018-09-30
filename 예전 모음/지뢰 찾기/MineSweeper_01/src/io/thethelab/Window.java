package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public void settings() {
        size(Constants.BLOCK_COUNT_X * Constants.BLOCK_SIZE,
                Constants.BLOCK_COUNT_Y * Constants.BLOCK_SIZE);
    }

    private List<View> views = new ArrayList<>();
    private Map mineMap = new Map(this);

    public void setup() {
        views.add(mineMap);
    }

    public void draw() {
        for (View view : views) {
            view. render();
        }
    }

}
