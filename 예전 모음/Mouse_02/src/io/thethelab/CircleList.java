package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class CircleList extends View {

    List<View> circleList = new ArrayList<>();

    public CircleList(PApplet pApplet) {
        super(pApplet, -1, -1);
    }

    @Override
    public void update() {
        for (View circle : circleList) {
            circle.update();
        }
    }

    @Override
    public void render() {
        for (View circle : circleList) {
            circle.render();
        }
    }

    @Override
    public boolean isSelected(int mouseX, int mouseY) {
        return false;
    }
}
