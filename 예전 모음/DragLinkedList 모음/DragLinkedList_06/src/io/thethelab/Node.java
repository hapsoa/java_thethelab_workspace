package io.thethelab;

import processing.core.PApplet;

public class Node extends View {

    boolean clicked;
    LinkList linkedList;
    int nodeNum;

    public Node(PApplet pApplet, LinkList linkedList) {
        super(pApplet, Constant.NODE_X, 30, 30, 30);
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
        // 마우스 누른 상태일때
        if(isCollision(pApplet.mouseX, pApplet.mouseY) && pApplet.mousePressed) {

            x = pApplet.mouseX - 15;
            y = pApplet.mouseY - 15;

            clicked = true;
        } else if (clicked) { //버튼을 뗄 때
            // 노드를 LinkedList에 추가한다. (노드가 linkedList에 포함되어 있지 않으면)
            if (y > 100 && !linkedList.linkedList.contains(this)) {
                int index = -1;

                for (Node node : linkedList.linkedList) { //노드 추가하기
                    if (x+15 > node.x && x+15 < node.x+node.width &&
                            y+15 > node.y && y+15 < node.y+node.height) {
                        index = linkedList.linkedList.indexOf(node);
                    }
                }
                if (index != -1) {
                    linkedList.linkedList.add(index, this);
                } else {
                    linkedList.addNode(this);
                }

            }


            //자동 위치 정렬
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
