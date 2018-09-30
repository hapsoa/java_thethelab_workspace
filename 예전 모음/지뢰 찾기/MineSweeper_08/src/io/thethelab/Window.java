package io.thethelab;

import processing.core.PApplet;

import javax.annotation.Resource;
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

        ResourceManager.init(this);

        Constants.MINE_IMAGE = ResourceManager.loadIamge(ResourceManager.MINE);
        Constants.FLAG_IMAGE = ResourceManager.loadIamge(ResourceManager.FLAG);

    }

    public void draw() {
        background(0);

        for (View view : views) {
            view.render();
        }


        if (keyPressed) { //키 눌렀을 때
            isKeyPressed = true;

            for (View view : views) {
                view.standBy();
            }

        } else {
            if (isKeyPressed) { // 키 뗐을 때
                System.out.println("hi");
                for (View view : views)
                    view.update();

                isKeyPressed = false;
            }
        }

    }


}
