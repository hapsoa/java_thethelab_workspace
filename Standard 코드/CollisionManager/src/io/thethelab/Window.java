package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public void settings() {
        size(800, 600);
    }

    List<View> views = new ArrayList<>();
    Wall wall = new Wall(this);
    List<Block> blocks = new ArrayList<>();
    Ball ball = new Ball(this);
//    Rect movingRect = new Rect(this);

    public void setup() {

        views.add(wall);

        blocks.add(new Block(this, 250, 200, 15, 20));
        blocks.add(new Block(this, 300, 200, 15, 20));
        blocks.add(new Block(this, 350, 200, 15, 20));
        blocks.add(new Block(this, 400, 200, 15, 20));
        blocks.add(new Block(this, 450, 200, 15, 20));

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
