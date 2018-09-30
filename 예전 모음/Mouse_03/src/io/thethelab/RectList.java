package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class RectList extends View {

    List<View> rectList = new ArrayList<>();

    RectList(PApplet pApplet) {
        super(pApplet, -1, -1);
    }

    @Override
    public void update() {
        for (View rect : rectList) {
            rect.update();
        }
    }

    @Override
    public void render() {
        for (View rect : rectList) {
            rect.render();
        }
    }

    @Override
    public boolean isSelected(int mouseX, int mouseY) {
        for (View rect : rectList) {
            if (rect.isSelected(pApplet.mouseX, pApplet.mouseY)) {
                draggingObject = rect;
                return true;
            }
        }

        return false;
    }
}
