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

        for (int i = 0; i < mineMap.blocks.size(); i++) {
            System.out.print(mineMap.blocks.get(i).property + " ");
            if (i == 2 || i == 5)
                System.out.println();
        }

        Constants.MINE_IMAGE = loadImage("bomb.png");
        Constants.FLAG_IMAGE = loadImage("flag.png");
    }

    public void draw() {
        for (View view : views) {
            view. render();
        }
    }

    public void keyReleased() {
        for (View view : views)
            view.update();
    }

}
