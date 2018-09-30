package io.thethelab;

import processing.core.PApplet;

public class Block extends View {

    int index;
    boolean visible;
    int item;
    int color;


    public Block(PApplet pApplet, int index) {
        super(pApplet);
        this.index = index;

        vector2.x = Utils.getPosXByIndex(index);
        vector2.y = Utils.getPosYByIndex(index);
    }



    @Override
    public void standBy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

        if (color == 1)
            pApplet.fill(153, 153, 153);
        else if (color == 2)
            pApplet.fill(255, 153, 0);
        else if (color == 3)
            pApplet.fill(0, 153, 0);
        else if (color == 0) {
            visible = false;
            return;
        }

        pApplet.rect(vector2.x, vector2.y, Constants.BLOCK_X, Constants.BLOCK_Y);
    }


}
