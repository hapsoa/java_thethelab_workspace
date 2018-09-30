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
    private boolean isKeyPressed;

    public void setup() {
        views.add(mineMap);

        Constants.MINE_IMAGE = loadImage("bomb.png");
        Constants.FLAG_IMAGE = loadImage("flag.png");

    }

    public void draw() {
        for (View view : views) {
            view.render();
        }


        if (keyPressed) { //키 눌렀을 때
            isKeyPressed = true;

//            for (View view : views) {
//
//            }
//            mineMap.standBy();

        } else if (isKeyPressed) { // 키 뗐을 때
            System.out.println("hi");
            for (View view : views)
                view.update();

            isKeyPressed = false;
        }

    }


}
