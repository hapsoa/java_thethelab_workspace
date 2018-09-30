package io.thethelab;

import processing.core.PApplet;

public class Node extends View {
    public Node(PApplet pApplet, int x, int y, int width, int height) {
        super(pApplet, x, y, width, height);
    }

    boolean clicked;

    @Override
    void render() {
        pApplet.fill(0, 255, 0);
        pApplet.rect(x, y, width, height);
    }

    void mouseClicked() {
        if(isCollision(pApplet.mouseX, pApplet.mouseY)) {

        }

        if (pApplet.mousePressed) {
            clicked = true;
        } else if (clicked) { //버튼을 뗄 때
            // 노드를 LinkedList에 추가한다.

            clicked = false;
        }
    }
}
