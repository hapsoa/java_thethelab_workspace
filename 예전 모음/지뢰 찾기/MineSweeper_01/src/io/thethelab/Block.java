package io.thethelab;

import processing.core.PApplet;

import java.util.List;

public class Block extends View {
    boolean visible;
    int property;
    int x, y;
    int index;
    int neighborMineCount;

    List<Block> aroundBlocks;

    public Block(PApplet pApplet, boolean visible, int index) {
        super(pApplet);
        this.visible = visible;
        this.property = property;
        this.index = index;
        x = Utils.getPosXByIndex(index);
        y = Utils.getPosYByIndex(index);
    }

    @Override
    void update() {

    }

    @Override
    void render() {
        pApplet.fill(150);
        pApplet.rect(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
    }

    public void setMine() {
        property = Constants.MINE;
    }
}
