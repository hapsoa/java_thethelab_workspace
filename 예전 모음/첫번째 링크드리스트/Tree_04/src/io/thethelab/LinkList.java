package io.thethelab;

import processing.core.PApplet;

import java.util.LinkedList;

public class LinkList extends View{

    LinkList(PApplet pApplet) {
        super(pApplet, 0, 0, 0, 0);
    }

    LinkedList<RectObject> rectObjects = new LinkedList<>();

    public void addRectObject(int index) {
        RectObject object = new RectObject(pApplet,
                100 + index * 80, 300,
                40, 40, index);

        rectObjects.add(object);
    }

    @Override
    public void render() {
        for (View view : rectObjects) {
            view.render();
        }
    }

    @Override
    public void mouseClicked() {
        int index = -1;

        for (RectObject object : rectObjects) {
            object.mouseClicked();
            if (object.clicked) {
                index = object.index;
                object.clicked = false;
            }
        }

        if (index != -1) {
            rectObjects.add(index, new RectObject(pApplet,
                    rectObjects.get(index).x + 80,
                    300, 40, 40, index+1));
            index = -1;
            System.out.println(rectObjects.size());
        }


    }


}
