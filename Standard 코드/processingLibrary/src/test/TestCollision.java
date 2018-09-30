package test;

import Manager.CollisionManager;
import Manager.View;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class TestCollision extends PApplet {

    public static void main(String[] args) {
        TestCollision.main("test.TestCollision");
    }

    public void settings() {
        size(800, 600);
    }

    List<View> views = new ArrayList<>();
    Wall wall = new Wall(this);
    List<Rect> blocks = new ArrayList<>();
    Ball ball = new Ball(this);
//    Rect movingRect = new Rect(this);

    public void setup() {

        views.add(wall);

        blocks.add(new Rect(this,View.RECT_OUTSIDE, 250, 200, 15, 20));
        blocks.add(new Rect(this, View.RECT_OUTSIDE,300, 200, 15, 20));
        blocks.add(new Rect(this, View.RECT_OUTSIDE,350, 200, 15, 20));
        blocks.add(new Rect(this, View.RECT_OUTSIDE,400, 200, 15, 20));
        blocks.add(new Rect(this,View.RECT_OUTSIDE, 450, 200, 15, 20));

        views.addAll(blocks);
        views.add(ball);
//        views.add(movingRect);

    }

    public void draw() {
        background(0);

        for (View view : views) {
            view.update();
            view.render();
        }

        CollisionManager.update();
    }

}
