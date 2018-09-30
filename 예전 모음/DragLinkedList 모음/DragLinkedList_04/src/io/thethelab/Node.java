package io.thethelab;

import processing.core.PApplet;

public class Node extends View {
    public Node(PApplet pApplet) {
        super(pApplet, 450, 30, 30, 30);
    }

    boolean clicked;

    @Override
    void render() {
        pApplet.fill(0, 255, 0);
        pApplet.rect(x, y, width, height);
    }

    @Override
    void update() {
        if(isCollision(pApplet.mouseX, pApplet.mouseY)) {
            if (pApplet.mousePressed) {
                x = pApplet.mouseX - 15;
                y = pApplet.mouseY - 15;

                clicked = true;
            } else if (clicked) { //버튼을 뗄 때
                // 노드를 LinkedList에 추가한다.
                if (y > 100) {

//                    linkedList.add(this);
                }

                clicked = false;
            }
        }

    }

    void mouseClicked() {

    }

    @Override
    void mousePressed() {
        if(isCollision(pApplet.mouseX, pApplet.mouseY)) {
            if (pApplet.mousePressed) {
                System.out.println("mouse Pressed!");
                update();

                clicked = true;
            } else if (clicked) { //버튼을 뗄 때
                // 노드를 LinkedList에 추가한다.

                clicked = false;
            }
        }
    }


}
