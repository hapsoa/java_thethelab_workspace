package io.thethelab;

import processing.core.PApplet;

public class AddNodeButton extends View{

    public AddNodeButton(PApplet pApplet, int x, int y, int width, int height) {
        super(pApplet, x, y, width, height);
    }

    @Override
    void render() {
        pApplet.fill(255, 0, 0);
        pApplet.rect(x, y, width, height);
    }

    void mouseClicked() {
        if(isCollision(pApplet.mouseX, pApplet.mouseY)) {
            System.out.println("Click!");
            // 노드를 추가한다

        }
    }
}
