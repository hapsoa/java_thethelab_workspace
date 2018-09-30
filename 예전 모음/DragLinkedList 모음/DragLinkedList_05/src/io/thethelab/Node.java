package io.thethelab;

import processing.core.PApplet;

public class Node extends View {

    boolean clicked;
    LinkList linkedList;
    int nodeNum;

    public Node(PApplet pApplet, LinkList linkedList) {
        super(pApplet, 450, 30, 30, 30);
        this.linkedList = linkedList;
        nodeNum = linkedList.linkedList.size();
    }

    @Override
    void render() {
        pApplet.fill(0, 255, 0);
        pApplet.rect(x, y, width, height);
        pApplet.fill(255);
        pApplet.text(nodeNum, x, y);
    }

    @Override
    void update() {
        if(isCollision(pApplet.mouseX, pApplet.mouseY) && pApplet.mousePressed) {

            x = pApplet.mouseX - 15;
            y = pApplet.mouseY - 15;

            clicked = true;
        } else if (clicked) { //버튼을 뗄 때
            // 노드를 LinkedList에 추가한다. (노드가 linkedList에 포함되어 있지 않으면)
            if (y > 100 && !linkedList.linkedList.contains(this)) {
                linkedList.addNode(this);
            }

            for (Node node : linkedList.linkedList) {
                node.x = 450;
                node.y = 120 + 50 * linkedList.linkedList.indexOf(node);
            }

            clicked = false;
        }


    }

    void mouseClicked() {

    }



}
