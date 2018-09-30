package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

class Block extends View {
    boolean visible;
    boolean isFlag;
    int property;
    int x, y;
    private int index;
    int neighborMineCount;
    int numOfFlagAround;

    List<Block> aroundBlocks;

    Block(PApplet pApplet, boolean visible, int index) {
        super(pApplet);
        this.visible = visible;
        isFlag = false;
        this.index = index;
        x = Utils.getPosXByIndex(index);
        y = Utils.getPosYByIndex(index);
        aroundBlocks = new ArrayList<>();

    }

    void makeConnection(List<Block> blocks) {
        neighborMineCount = 0;

        if (!Utils.isLeft(index) && !Utils.isUp(index)) {
            aroundBlocks.add(blocks.get(index - 1 - Constants.BLOCK_COUNT_X));
        }

        if (!Utils.isUp(index)) {
            aroundBlocks.add(blocks.get(index - Constants.BLOCK_COUNT_X));
        }

        if (!Utils.isUp(index) && !Utils.isRight(index)) {
            aroundBlocks.add(blocks.get(index + 1 - Constants.BLOCK_COUNT_X));
        }

        if (!Utils.isRight(index)) {
            aroundBlocks.add(blocks.get(index + 1));
        }

        if (!Utils.isRight(index) && !Utils.isDown(index)) {
            aroundBlocks.add(blocks.get(index + 1 + Constants.BLOCK_COUNT_X));
        }

        if (!Utils.isDown(index)) {
            aroundBlocks.add(blocks.get(index + Constants.BLOCK_COUNT_X));

        }

        if (!Utils.isLeft(index) && !Utils.isDown(index)) {
            aroundBlocks.add(blocks.get(index - 1 + Constants.BLOCK_COUNT_X));
        }

        if (!Utils.isLeft(index)) {
            aroundBlocks.add(blocks.get(index - 1));
        }

        for (Block aroundBlock : aroundBlocks) {
            if (aroundBlock.property == Constants.MINE)
                neighborMineCount++;
        }

        if (property != Constants.MINE)
            property = neighborMineCount;

        setNumOfFlagAround();

    }

    private void setNumOfFlagAround() {
        numOfFlagAround = 0;
        for (Block block : aroundBlocks) {
            if (block.isFlag) {
                numOfFlagAround++;
            }
        }
    }

    boolean isFlagAndMineSame() {
        int numOfRightFlag = 0;
        for (Block block : aroundBlocks) {
            if (block.isFlag && block.property == Constants.MINE)
                numOfRightFlag++;
        }

        return numOfFlagAround == numOfRightFlag;

    }


    @Override
    void update() {

    }

    @Override
    void render() {
        if (!visible) {
            pApplet.fill(150);
            pApplet.rect(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);

            if (isFlag) { // 깃발
                pApplet.image(Constants.FLAG_IMAGE, x + 6, y + 6);
            }

        } else { // 블록이 보일 때
            pApplet.fill(200);
            pApplet.rect(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);

            if (property == Constants.N0_MINE) {

            } else if (property == Constants.MINE) {
                pApplet.image(Constants.MINE_IMAGE, x + 6, y + 6);
            } else {
                pApplet.fill(255);
                pApplet.text(property, x + Constants.BLOCK_SIZE / 2,
                        y + Constants.BLOCK_SIZE / 2);
            }

        }
    }

    void renderLighter() {
        pApplet.fill(200);
        pApplet.rect(x, y,
                Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
    }

    @Override
    void standBy() {

    }

    void setMine() {
        property = Constants.MINE;
    }
}
