package io.thethelab;

import processing.core.PApplet;

import java.util.List;

public class AddNodeButton extends View {

    List<View> views;
    boolean makingNode = false;

    public AddNodeButton(PApplet pApplet, int x, int y, int width, int height, List<View> views) {
        super(pApplet, x, y, width, height);
        this.views = views;
    }

    @Override
    void render() {
        pApplet.fill(255, 0, 0);
        pApplet.rect(x, y, width, height);
    }

    @Override
    void update() {

    }

    void mouseClicked() {
        if (isCollision(pApplet.mouseX, pApplet.mouseY)) {

            // 노드를 추가한다
            makingNode = true;
            System.out.println("new Node!");

        }
    }



}

