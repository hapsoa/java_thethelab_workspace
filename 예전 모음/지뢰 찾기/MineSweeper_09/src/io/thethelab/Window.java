package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Window extends PApplet {

    public void settings() {
//        size(200, 200);
//
//        Constants.BLOCK_COUNT_X = sc.nextInt();
//        Constants.BLOCK_COUNT_Y = sc.nextInt();

        size(Constants.BLOCK_COUNT_X * Constants.BLOCK_SIZE,
                Constants.BLOCK_COUNT_Y * Constants.BLOCK_SIZE + Constants.UI_HEIGHT);
    }

    private List<View> views = new ArrayList<>();
    Map mineMap = new Map(this);
    private UIBar uiBar = new UIBar(this);
    private boolean isKeyPressed;
//    private Scanner sc = new Scanner(System.in);

    public void setup() {


        background(255);

        views.add(mineMap);
        views.add(uiBar);

        ResourceManager.init(this);

        Constants.MINE_IMAGE = ResourceManager.loadImage(ResourceManager.MINE);
        Constants.FLAG_IMAGE = ResourceManager.loadImage(ResourceManager.FLAG);
        Constants.SMILE = ResourceManager.loadImage(ResourceManager.SMILE_HAPPY);

    }

    public void draw() {
        background(255);

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
