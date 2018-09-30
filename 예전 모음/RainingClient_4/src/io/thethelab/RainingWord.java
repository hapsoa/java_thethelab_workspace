package io.thethelab;

import processing.core.PApplet;

public class RainingWord extends MovableView {

    String word;

    RainingWord(PApplet pApplet, String word) {
        super(pApplet, SHAPE_NONE);


        this.word = word;

        position.x = (float) (Math.random() * 800);
        position.y = 0;
        speed = (float) (Math.random() * 1 + 0.1f);
        direction.y = 1.0f;
        direction = direction.nomalize();

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
        pApplet.fill(0);
        pApplet.textSize(32);
        pApplet.text(word, position.x, position.y);
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isMouseCollision(float mouseX, float mouseY) {
        return false;
    }
}
