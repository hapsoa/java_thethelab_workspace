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

    }

    @Override
    void render() {

    }

    void mouseClicked() {
        for (Node node : linkedList) {
            node.mouseClicked();
        }
    }
}
