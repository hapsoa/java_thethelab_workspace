package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {
    public void settings() {
        size(960, 640);
    }

    private List<View> views = new ArrayList<>();
    // 추가 버튼
    private LinkList linkedList = new LinkList(this); // LinkedList
    private AddNodeButton addNodeButton = new AddNodeButton(this,5, 5, 30, 30, views);  // 추가 버튼
    private AscendingSortButton ascendingSortButton =
            new AscendingSortButton(this, 900, 5, 30, 30,
                    linkedList.linkedList);
    private DescendingSortButton descendingSortButton =
            new DescendingSortButton(this, 900, 50, 30, 30,
                    linkedList.linkedList);

    public void setup() {
        views.add(addNodeButton);
        views.add(ascendingSortButton);
        views.add(descendingSortButton);
        views.add(linkedList);
    }

    public void draw() {
        background(0);

        for (View view : views) {
            view.update();
            view.render();
        }

        // 노드 삭제
        for (int i = 0; i < views.size(); i++) {
            if (views.get(i).deleteTrue) {
                views.remove(i);
                System.out.println("views remove!");
                break;
            }
        }



    }

    public void mouseClicked() {
        for (View view : views) {
            view.mouseClicked();
        }

        //
        if (addNodeButton.makingNode) {
            views.add(new Node(this, linkedList));
            addNodeButton.makingNode = false;
        }



    }



}
