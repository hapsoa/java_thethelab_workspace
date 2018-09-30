package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class LinkList extends View {
    List<Node> linkedList = new LinkedList<>();

    LinkList(PApplet pApplet) {
        super(pApplet, 0, 0, 0, 0);
    }

    public void addNode(Node node) {
        linkedList.add(node);
        node.x = 450;
        node.y = 120 + 50 * linkedList.size();

        System.out.println("addNode!");
    }

    @Override
    void render() {
        for (Node node : linkedList) {
            node.render();
        }
    }
    @Override
    void update() {

    }

    void mouseClicked() {
        for (Node node : linkedList) {
            node.mouseClicked();
        }
    }




}
