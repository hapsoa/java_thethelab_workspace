package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public void settings() {
        size(Constant.APPLET_WIDTH, Constant.APPLET_HEIGHT);
    }

    private List<View> views = new ArrayList<>();
    private CircleList circles = new CircleList(this);
    private RectList rects = new RectList(this);

    private View draggingObject = null;

    public void setup() {
        views.add(circles);
        views.add(rects);
    }

    public void draw() {
        background(150);

//        if (mousePressed) {
//            if (draggingObject == null) {
//                for (View view : views) {
//
//                }
//            }
//        }

        for (View view : views) {
            view.update();
            view.render();
        }
    }

    public void keyReleased() {
        checkCreatingFigure();
    }

    public void checkCreatingFigure() {
        if (key == '1') {
            System.out.println(1);
            circles.circleList.add(
                    new Circle(this,
                            (int)(Math.random()*Constant.APPLET_WIDTH),
                            (int)(Math.random()*Constant.APPLET_HEIGHT),
                            (int)(Math.random()*30)+10));
        }
        if (key == '2') {
            System.out.println(2);
        }
    }

}
