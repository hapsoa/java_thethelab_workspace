package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public void settings() {
        size(Constants.APPLET_WIDTH, Constants.APPLET_HEIGHT);
    }

    List<View> views = new ArrayList<>();
    Map map = new Map(this);

    private boolean isKeyPressed = false;

    public void setup() {
        views.add(map);
    }

    public void draw() {
        background(255);

        if (keyPressed) { // 키 누를 시
            for (View view : views)
                view.standBy();

            isKeyPressed = true;
        }
        else if (isKeyPressed) { // 키 뗄 시
            for (View view : views)
                view.update();

            isKeyPressed = false;
        }

        for (View view : views)
            view.render();

    }

}
