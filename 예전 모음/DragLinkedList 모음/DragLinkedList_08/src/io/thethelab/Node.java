package io.thethelab;

import processing.core.PApplet;

class Node extends View {

    private boolean clicked = false;
    private boolean maintainBool = false;
    private LinkList linkedList;
    int nodeNum;

    Node(PApplet pApplet, LinkList linkedList) {
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

            maintainBool = true;
            clicked = true;
        } else if (pApplet.mousePressed && maintainBool) { // 노드 밖으로 이동해도 이동 유지

            x = pApplet.mouseX - 15;
            y = pApplet.mouseY - 15;

        } else if(clicked) { //버튼을 뗄 때
            // 노드를 LinkedList에 추가한다. (노드가 linkedList에 포함되어 있지 않으면)
            if (y > 100) {
                if (!linkedList.linkedList.contains(this)) {
                    int index = -1;

                    for (Node node : linkedList.linkedList) { //노드 추가하기
                        if (x + 15 > node.x && x + 15 < node.x + node.width &&
                                y + 15 > node.y && y + 15 < node.y + node.height) {
                            index = linkedList.linkedList.indexOf(node);
                        }
                    }
                    if (index != -1) {
                        linkedList.linkedList.add(index, this);
                        //views에서는 삭제
                    } else {
                        linkedList.addNode(this);
                        System.out.println(linkedList.linkedList);
                    }
                }
                else { //링크드 리스트안에 노드가 있을 때
                    if (x < 200 || x > 760) {
                        linkedList.linkedList.remove(this);
                        System.out.println("remove!");
                        System.out.println(linkedList.linkedList);
                        deleteTrue = true;
                    }
                }

            }



            //자동 위치 정렬
            for (Node node : linkedList.linkedList) {
                node.x = 450;
                node.y = 120 + 50 * linkedList.linkedList.indexOf(node);
            }

            maintainBool = false;
            clicked = false;
        }


    }

    void mouseClicked() {

    }





}
