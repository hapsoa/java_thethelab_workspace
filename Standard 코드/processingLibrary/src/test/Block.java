package test;

import Manager.MovableView;
import Manager.View;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Block extends MovableView {

    private List<Rect> rects = new ArrayList<>();

    Block(PApplet pApplet, int shape) {
        super(pApplet, shape);
        rects.add(new Rect(pApplet, View.RECT_OUTSIDE, 115, 145, 30, 30));
        rects.add(new Rect(pApplet, View.RECT_OUTSIDE, 145, 145, 30, 30));
        rects.add(new Rect(pApplet, View.RECT_OUTSIDE, 145, 115, 30, 30));
        rects.add(new Rect(pApplet, View.RECT_OUTSIDE,175, 115, 30, 30));

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {


        for (Rect rect : rects)
            rect.render();

    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

    public List<Rect> getRects() {
        return rects;
    }
}
