package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Window extends PApplet {

    public void settings() {
        size(Constants.X_COUNT * Constants.BLOCK_SIZE,
                Constants.Y_COUNT * Constants.BLOCK_SIZE);
    }


    Scanner sc = new Scanner(System.in);
    int numOfMine;
    List<View> views = new ArrayList<>();
    private boolean isPressed;

    public void setup() {
        System.out.print("폭탄 수를 입력하세요 : ");
        numOfMine = sc.nextInt();
        ResourceManagers.init(this);

        Map mineMap = new Map(this);
        views.add(mineMap);

        for (int i = 0; i < mineMap.blocks.size(); i++) {
            System.out.print(mineMap.blocks.get(i).property);
            if (i % 5 == 4)
                System.out.println();
        }
    }

    public void draw() {
        background(0);

        if (keyPressed) { // 키 누르고 있을 때
            for (View view : views)
                view.standBy();

            isPressed = true;
        }
        else if (isPressed) { // 키를 뗄 때
            for (View view : views)
                view.update();

            isPressed = false;
        }

        for (View view : views) {
            view.render();
        }

    }



}
