package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public void settings() {
        size(960,640);
    }

    List<View> views = new ArrayList<>();

    public void setup() {
        LinkList linkedList = new LinkList(this);
        linkedList.addRectObject(new RectObject.OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("ok");
            }
        });

        views.add(linkedList);
    }

    public void draw() {
        background(0);

        for (View view : views) {
            view.render();
        }
    }

    public void mouseClicked() {
        for (View view : views) {
            view.mouseClicked();
        }
    }



}
