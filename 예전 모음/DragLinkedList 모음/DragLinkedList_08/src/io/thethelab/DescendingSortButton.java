package io.thethelab;

import processing.core.PApplet;

import java.util.Comparator;
import java.util.List;

public class DescendingSortButton extends View {

    List<Node> linkedList;
    private boolean sortBool = false;

    public DescendingSortButton(PApplet pApplet, int x, int y,
                               int width, int height, List<Node> linkedList) {
        super(pApplet, x, y, width, height);
        this.linkedList = linkedList;
    }

    @Override
    void render() {
        pApplet.fill(0, 0, 255);
        pApplet.rect(x, y, width, height);
        pApplet.fill(255);
        pApplet.text("Descending Sort", x-100, y+15);
    }

    @Override
    void update() {

    }

    @Override
    void mouseClicked() {
        if (isCollision(pApplet.mouseX, pApplet.mouseY)) {
            linkedList.sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o2.nodeNum - o1.nodeNum;
                }
            });

            //자동 위치 정렬
            for (Node node : linkedList) {
                node.x = 450;
                node.y = 120 + 50 * linkedList.indexOf(node);
            }

            System.out.println("sorted!");
        }
    }

}
