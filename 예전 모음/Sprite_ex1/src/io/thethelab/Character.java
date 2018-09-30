package io.thethelab;

import processing.core.PApplet;
import sun.security.krb5.internal.PAData;

public class Character extends View {
    PApplet pApplet;
    int x, y;

    Character(PApplet pApplet) {
        this.pApplet = pApplet;
        x = 30;
        y = 30;
    }

    @Override
    void play() {

    }

    @Override
    void render() {
        pApplet.image(ResourceManager.loadImage(
                ResourceManager.DOWN_0
        ), x, y);
    }
}
