package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class LinkList extends View {
    List<Node> linkedList = new ArrayList<>();

    public LinkList(PApplet pApplet) {
        super(pApplet, 0, 0, 0, 0);
    }

    public void addNode() {
        linkedList.add(new Node(pApplet));
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

    @Override
    void mousePressed() {
        for (Node node : linkedList) {
            node.mousePressed();
        }
    }


}
