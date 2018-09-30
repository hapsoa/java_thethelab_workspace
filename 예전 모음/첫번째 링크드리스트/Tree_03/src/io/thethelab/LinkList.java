package io.thethelab;

import processing.core.PApplet;

import java.util.LinkedList;
import java.util.List;

public class LinkList extends View{

    LinkList(PApplet pApplet) {
        super(pApplet, 0, 0, 0, 0);
    }

    private List<View> rectObjects = new LinkedList<>();

    public void addRectObject(OnClickListener onClickListener) {
        RectObject object = new RectObject(pApplet,
                100 + rectObjects.size() * 80, 300,
                40, 40);

        object.setOnClickListener(onClickListener);

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
        for (View view : rectObjects) {
            view.mouseClicked();
        }
    }
}
