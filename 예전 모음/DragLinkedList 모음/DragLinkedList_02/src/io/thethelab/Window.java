package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {
    public void settings() {
        size(960, 640);
    }

    List<View> views = new ArrayList<>();
    // 추가 버튼
    LinkList linkedList = new LinkList(this); // LinkedList
    AddNodeButton addNodeButton = new AddNodeButton(this,5, 5, 30, 30, views);  // 추가 버튼

    public void setup() {
        views.add(addNodeButton);
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

        if (addNodeButton.makingNode) {
            views.add(new Node(this));
            addNodeButton.makingNode = false;
        }
    }

    public void mousePressed() {
        for (View view : views) {
            view.mousePressed();
        }
//        linkedList.mousePressed();
    }

}
